package com.client;

import com.entity.AccountingVoucher;
import com.util.StringUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.List;

public class BootStrap {
	
    public  String  BootStrapClient(String host,int port,String sendData) throws IOException {
    	//1.创建Socket s=new Socket();
        
    	SocketChannel s=SocketChannel.open();
        //2.连接服务器s.connect(new InetSocketAddress(ip,9999));
        
    	s.connect(new InetSocketAddress(host,port));
    	ByteBuffer buffer_write=ByteBuffer.allocate(2048);
        //3.使用流操作请求和响应
    	//①发请求
        String res=sendData;
        int length1 = res.length();
        int length = res.getBytes().length;
        int count = length / (buffer_write.capacity());
        int yu_count = length % (buffer_write.capacity());
        if(yu_count!=0){
            count+=1;
        }
        if(length>buffer_write.capacity()) {
            List<String> list = StringUtil.cutStringByBite(res, buffer_write.capacity());
            for (int i = 0; i < list.size(); i++) {
                System.out.println(i+"数据:"+list.get(i));
                if (i != list.size() - 1) {
                    buffer_write.clear();
                    s.write(ByteBuffer.wrap(list.get(i).getBytes(), 0, buffer_write.capacity()));
                } else {
                    int yu_count1 = (list.get(i)).getBytes().length;
                    //System.out.println(yu_count1);
                    s.write(ByteBuffer.wrap((list.get(i)).getBytes(), 0, yu_count));
                }
            }
        }
        else {
            s.write(ByteBuffer.wrap(res.getBytes(), 0, yu_count));
        }
        
        s.socket().shutdownOutput();//告知服务请求内容发送结束
        //②读取服务器响应
        ByteBuffer buffer=ByteBuffer.allocate(1024);
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        while(true){
            buffer.clear();         
            int n = s.read(buffer);
            if(n==-1) break;
            buffer.flip();
            baos.write(buffer.array(),0,n);
        }        
        String recv =(new String(baos.toByteArray()));  //数据转码GBK
        recv=baos.toString("GBK");
        System.out.println("客户端收到："+recv);        
        //4.关闭socket资源
        s.close();
		return recv;
    }
    
    public Boolean Connection(String host,int port) throws IOException{
    	//1.创建Socket s=new Socket();        
    	SocketChannel s=SocketChannel.open();
        //2.连接服务器s.connect(new InetSocketAddress(ip,9999));        
    	boolean isConnect = s.connect(new InetSocketAddress(host,port));
    	return isConnect;
    }
        
    public void sendData(String data,SocketChannel s) throws IOException{
    	ByteBuffer buffer_write=ByteBuffer.allocate(2048);
        //3.使用流操作请求和响应
    	//①发请求
        String res=data;        
        int length = res.getBytes().length;
        int count = length / (buffer_write.capacity());
        int yu_count = length % (buffer_write.capacity());
        if(yu_count!=0){
            count+=1;
        }
        if(length>buffer_write.capacity()) {
            List<String> list = StringUtil.cutStringByBite(res, buffer_write.capacity());
            for (int i = 0; i < list.size(); i++) {
                System.out.println(i+"数据:"+list.get(i));
                if (i != list.size() - 1) {
                    buffer_write.clear();
                    s.write(ByteBuffer.wrap(list.get(i).getBytes(), 0, buffer_write.capacity()));
                } else {                 
                    s.write(ByteBuffer.wrap((list.get(i)).getBytes(), 0, yu_count));
                }
            }
        }
        else {
            s.write(ByteBuffer.wrap(res.getBytes(), 0, yu_count));
        }
        
    }
    
    public String readData(SocketChannel s,Boolean isConnection,String host,int port) throws IOException{
    	while(!isConnection){
    		for(int i=0;i<3;i++){
        		isConnection = Connection(host, port);
        		if(isConnection){        		
        			break;
        		}else{//重连三次  
        			if(i<=1){//第一次第二次重连失败
        				continue;
        			}else{ //第三次重连失败
        				throw new RuntimeException("三次重连失败,请检查服务端运行状态");        				
        			}       			
        		}
        	}
    	}
    	//②读取服务器响应
        ByteBuffer buffer=ByteBuffer.allocate(1024);
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        while(true){
            buffer.clear();         
            int n = s.read(buffer);
            if(n==-1) break;
            buffer.flip();
            baos.write(buffer.array(),0,n);
        }        
        String recv =(new String(baos.toByteArray()));  //数据转码GBK
        recv=baos.toString("GBK");
        System.out.println("客户端收到："+recv);
		return recv;
    }
   
    
    public  String  BootStrapClient1(String host,int port,String sendData) throws IOException{
    String recv="";
    Boolean isConnection=false;
    //1.创建Socket s=new Socket();        
    SocketChannel s=SocketChannel.open();
    //2.连接
    try{
        isConnection = Connection(host, port);
        if(!isConnection){ //连接失败，短线主动重连三次
        	for(int i=0;i<3;i++){
        		isConnection = Connection(host, port);
        		if(isConnection){        		
        			break;
        		}else{//重连三次  
        			if(i<=1){//第一次第二次重连失败
        				continue;
        			}else{ //第三次重连失败
        				throw new RuntimeException("三次重连失败,请检查服务端运行状态");        				
        			}       			
        		}
        	}
        }
    while(isConnection){
    	sendData(sendData,s);
     }
     s.socket().shutdownOutput();//告知服务请求内容发送结束
      recv = readData(s,isConnection,"localhost",8829);    
    }catch(RuntimeException e){
        e.getMessage();
        e.printStackTrace();
    }finally{
    	//4.关闭socket资源
        s.close();	
    }	
	return recv;
  }
    
  public static String dataFormat(List<AccountingVoucher> list){
	  String sendMeg ="";
	  if(list.size()>0){
			AccountingVoucher t=null;
			Iterator<AccountingVoucher> it = list.iterator();
			while (it.hasNext())// 判断是不是最后一个
			{
				t = it.next();				
				sendMeg+=(t.getDataFormat()+";");
			}
			//处理最后一条记录;
			sendMeg=sendMeg.substring(0, sendMeg.length()-1)+"#";						
	  }	 
	return sendMeg;
  }
}

