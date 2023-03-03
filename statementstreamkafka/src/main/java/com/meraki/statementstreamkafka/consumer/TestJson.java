package com.meraki.statementstreamkafka.consumer;

import com.google.gson.Gson;
import com.meraki.statementstreamkafka.entity.StatementEntity;
import org.json.JSONException;
import org.json.JSONObject;

public class TestJson {
    private final static String JSON_DATA =
            "{\"RECID\":\"201088875637496.000001\",\"XMLRECORD\":\"<row id='201088875637496.000001' xml:space='preserve'><c1>1106654056</c1><c2>KE0010001</c2><c3>500000.00</c3><c4>213</c4><c6>/</c6><c6 m='2'>MPESA RAJ11M05DF 254722123456 FSI</c6><c8>439995</c8><c9>4031</c9><c10>6001</c10><c11>20220214</c11><c12>KES</c12><c16>TR</c16><c17>FT220453H0Z1</c17><c19>20220214</c19><c20>1</c20><c22>4003</c22><c23>FT220453H0Z1</c23><c24>FT</c24><c25>20220214</c25><c26>201088875637496.00</c26><c26 m='2'>1-2</c26><c29>1</c29><c30>88756_COB</c30><c31>2301191024</c31><c32>88756_COB</c32><c36>CREDIT</c36><c39>AC.1.TR.KES.6001.3600.KE....3710.KE..4031...KE0010001</c39><c91>CLDEFAULT</c91><c100>20220214</c100><c104>1</c104><c106></c106></row>\"}";

    public static void main(final String[] argv) throws JSONException {

        final JSONObject obj = new JSONObject(JSON_DATA);

        System.out.println("RecId: " + obj.getString("RECID"));
        System.out.println("XmlRecord: " + obj.getString("XMLRECORD"));

    }

}
class Test2 {
    private final static String payload = "{\"RECID\":\"201088875637469.000001\",\"XMLRECORD\":\"<row id='201088875637496.000001' xml:space='preserve'><c1>1106654056</c1><c2>KE0010001</c2><c3>500000.00</c3><c4>213</c4><c6>/</c6><c6 m='2'>MPESA RAJ11M05DF 254722123456 FSI</c6><c8>439995</c8><c9>4031</c9><c10>6001</c10><c11>20220214</c11><c12>KES</c12><c16>TR</c16><c17>FT220453H0Z1</c17><c19>20220214</c19><c20>1</c20><c22>4003</c22><c23>FT220453H0Z1</c23><c24>FT</c24><c25>20220214</c25><c26>201088875637496.00</c26><c26 m='2'>1-2</c26><c29>1</c29><c30>88756_COB</c30><c31>2301191024</c31><c32>88756_COB</c32><c36>CREDIT</c36><c39>AC.1.TR.KES.6001.3600.KE....3710.KE..4031...KE0010001</c39><c91>CLDEFAULT</c91><c100>20220214</c100><c104>1</c104><c106></c106></row>\"}\n";

    public static void main(String[] args) {


        Gson gson = new Gson();

        StatementEntity statement = gson.fromJson(payload.toLowerCase(), StatementEntity.class);

        String recId = statement.getRecId();
        String xmlRecord = statement.getXmlRecord();

        System.out.println("statement "+ statement );
        System.out.println("recId "+ recId );
        System.out.println("xmlRecord "+ xmlRecord );



    }
}