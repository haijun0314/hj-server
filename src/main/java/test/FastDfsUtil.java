package test;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

public class FastDfsUtil {
	public static void main(String[] args)  throws Exception {
	    // 1、把FastDFS提供的jar包添加到工程中
	    // 2、初始化全局配置。加载一个配置文件。
	    ClientGlobal.init("D:\\work\\fastdfs_client.conf");
	    // 3、创建一个TrackerClient对象。
	    TrackerClient trackerClient = new TrackerClient();
	    // 4、创建一个TrackerServer对象。
	    TrackerServer trackerServer = trackerClient.getConnection();
	    // 5、声明一个StorageServer对象，null。
	    StorageServer storageServer = null;
	    // 6、获得StorageClient对象。
	    StorageClient storageClient = new StorageClient(trackerServer,  storageServer);
	    // 7、直接调用StorageClient对象方法上传文件即可。
	    String[] strings = storageClient.upload_file("D:\\xiazai\\2.jpg", "jpg", null);
	    for (String string : strings) {
	        System.out.println(string);
	    }
	}
}
