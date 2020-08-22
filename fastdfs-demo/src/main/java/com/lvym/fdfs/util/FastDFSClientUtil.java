package com.lvym.fdfs.util;


import org.csource.fastdfs.*;



public class FastDFSClientUtil {
    public static void main(String[] args) {
        upload();
      // downLoad();
        //delete();
    }

    private static void delete() {
        TrackerServer trackerServer =null;
        StorageServer storeStorage=null;
        try {
            ClientGlobal.init("fastdfs.conf");
            //
            TrackerClient trackerClient = new TrackerClient();
            trackerServer = trackerClient.getTrackerServer();
            storeStorage = trackerClient.getStoreStorage(trackerServer);
            StorageClient storageClient = new StorageClient(trackerServer,storeStorage);
            /**
             *  组，文件长名
             */
            int deleteFile = storageClient.delete_file("group1","M00/00/00/wKiSjF85_3uAft4mAAO0-BD7IsE044.jpg");
            System.out.println(deleteFile);


        } catch (Exception e) {

        }

    }

    private static void downLoad() {
        TrackerServer trackerServer =null;
        StorageServer storeStorage=null;
        try {
            ClientGlobal.init("fastdfs.conf");
            //
            TrackerClient trackerClient = new TrackerClient();
            trackerServer = trackerClient.getTrackerServer();
            storeStorage = trackerClient.getStoreStorage(trackerServer);
            StorageClient storageClient = new StorageClient(trackerServer,storeStorage);
            /**
             * 文件路径，文件拓展名，文件的属性文件
             */
            int downloadFile = storageClient.download_file("group1", "M00/00/00/wKiSjF85_3uAft4mAAO0-BD7IsE044.jpg", "C:\\Users\\14829\\Desktop\\a.jpg");
            System.out.println(downloadFile);

        } catch (Exception e) {

        }
    }

    private static void upload() {
        TrackerServer trackerServer =null;
        StorageServer storeStorage=null;
        try {
            ClientGlobal.init("fastdfs.conf");
            //
            TrackerClient trackerClient = new TrackerClient();
             trackerServer = trackerClient.getTrackerServer();
             storeStorage = trackerClient.getStoreStorage(trackerServer);
            StorageClient storageClient = new StorageClient(trackerServer,storeStorage);
            /**
             * 文件路径，文件拓展名，文件的属性文件
             */
            String[] uploadFile = storageClient.upload_file("D:\\ym\\psb4ASJ96MH.jpg", "jpg", null);
            for (String s : uploadFile) {
                System.out.println(s);
            }

        } catch (Exception e) {

        }
    }
}
