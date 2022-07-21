package hi.core.lifeCycle;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient {
    private String url;

    public NetworkClient(){
        System.out.println("생성자 호출, url = " + url);
//        connect();
//        call("초기화 연결 메시지");
    }

    public void setUrl(String url) {
        this.url = url;

    }

    public void connect(){
        System.out.println("connect = " + url);
    }

    public void call(String messasge){
        System.out.println("call : " + url + ",  message = " +messasge);
    }

    //서비스 종료시 호출
    public void disconnect(){
        System.out.println("close : " + url);
    }

    //의존관계 끝나면 설정해줌
    @PostConstruct
    public void init() throws Exception {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");

    }

    @PreDestroy
    public void close() throws Exception {
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
