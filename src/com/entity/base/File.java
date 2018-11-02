package com.entity.base;

public class File {
    //获取文件URL，从数据库提取一个文件的url
    protected String url;
    //文件类型
    private String fileType;
    //文件名
    private String fileName;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFileType() {
        return fileType;
    }

    private void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileName() {
        return fileName;
    }

    private void setFileName(String fileName) {
        this.fileName = fileName;
    }

    //解析url中的文件名
    public void decodeFileName(){
        //创建一个暂时存储文件名的变量
        StringBuilder temp = new StringBuilder();
        for(int i = 0;i < url.length();i++){
            if(url.charAt(i) == '/'){
                int wordLength = i + 1;
                while (wordLength < url.length() && url.charAt(wordLength) != '.'){
                    temp.append(url.charAt(wordLength));
                    wordLength++;
                }
            }
        }
        //把解析得到的文件名设置为属性
        this.setFileName(temp.toString());
    }

    //解析url中的文件类型
    public void decodeFileType(){
        //创建一个暂时的存储文件类型的变量
        StringBuilder temp = new StringBuilder();
        for(int i = 0;i < url.length();i++) {
            if (url.charAt(i) == '.'){
                int wordLength = i + 1;
                while(wordLength < url.length()){
                    temp.append(url.charAt(wordLength));
                    wordLength++;
                }
            }
        }
        this.setFileType(temp.toString());
    }

}
