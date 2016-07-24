package com.example;

import java.rmi.RemoteException;

/**
 * Created by ZuoShu on 6/17/16.
 */
public class Webservice {
    public static void main(String[] args) {
        liveOpt(12, "token", "");
        liveOpt(13, "token", "");
        liveOpt(14, "token", "{\"room_id\":12345}");
        liveOpt(15, "token", "");
        liveOpt(16, "token", "");
        liveOpt(17, "token", "");
        liveOpt(18, "token", "{\n" +
                "    \"gift\": {\n" +
                "        \"gift_id\": \"2\"\n" +
                "    }\n" +
                "}");
    }

    static void liveOpt(int optCode, String token, String args) {
        try {
            LemoninfoStub stub1 = new LemoninfoStub("http://121.41.10.55:4568");
            LemoninfoStub.Liveopt liveopt = new LemoninfoStub.Liveopt();
            liveopt.setOptcode(optCode);
            liveopt.setToken(token);
            liveopt.setOptargs(args);
            LemoninfoStub.LiveoptResponse liveoptResponse = stub1.liveopt(liveopt);

            System.out.println("optCode:" + optCode
                    + " token:" + token
                    + " args:" + args
                    + " menusResponse:" + liveoptResponse.getResult());

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
