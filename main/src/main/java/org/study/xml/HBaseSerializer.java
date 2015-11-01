package org.study.xml;

/**
 * Created by yur on 28.10.2015.
 */
public class HBaseSerializer {
    private static final String testXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> <CellSet>\n" +
            " <Row key=\"NGY0ZTU2N2ItYjQxNi00ODE5LThhNDYtYTEzYTI0YzM4OTM5\">\n" +
            "<Cell column=\"Y29tbW9uOmFyZWE=\" timestamp=\"1445954340846\">MTIzLjAz</Cell>\n" +
            "<Cell column=\"Y29tbW9uOmg=\" timestamp=\"1445954454171\">MTMuMDM=</Cell>\n" +
            "<Cell column=\"Y29tbW9uOm5hbWU=\" timestamp=\"1445954117510\">TXkgRmlyc3QgRmllbGQ=</Cell>\n" +
            "<Cell column=\"Y29tbW9uOnc=\" timestamp=\"1445954462132\">MjcuOTI=</Cell>\n" +
            "<Cell column=\"Y29vcmRzOg==\" timestamp=\"1445954701452\">MTEyMjM0</Cell>\n" +
            "<Cell column=\"Y29vcmRzOjE=\" timestamp=\"1445954647780\">MjcuOTI=</Cell>\n" +
            "<Cell column=\"Y29vcmRzOmNlbnRlcg==\" timestamp=\"1445954290236\">Mi4wMw==</Cell>\n" +
            " </Row>\n" +
            " <Row key=\"ZDM4Nzg1NTYtZTZkNC00ODQyLTkyYWYtZDczYzg3NWRjZDZi\">\n" +
            "<Cell column=\"Y29tbW9uOmFyZWE=\" timestamp=\"1446034050485\">MTIzLjAz</Cell>\n" +
            "<Cell column=\"Y29tbW9uOm5hbWU=\" timestamp=\"1446034042256\">U2Vjb25kIGZpZWxk</Cell>\n" +
            "<Cell column=\"Y29vcmRzOmNlbnRlcg==\" timestamp=\"1446034054464\">WzEuMSwgMi4wXQ==</Cell>\n" +
            " </Row>\n" +
            " </CellSet>";

    private static final String testXml1 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            " <Row key=\"NGY0ZTU2N2ItYjQxNi00ODE5LThhNDYtYTEzYTI0YzM4OTM5\">\n" +
            "<name>aaaa</name>\n" +
            "<Cell column=\"Y29tbW9uOmFyZWE=\" timestamp=\"1445954340846\">MTIzLjAz</Cell>\n" +
            "<Cell column=\"Y29tbW9uOmg=\" timestamp=\"1445954454171\">MTMuMDM=</Cell>\n" +
            "<Cell column=\"Y29tbW9uOm5hbWU=\" timestamp=\"1445954117510\">TXkgRmlyc3QgRmllbGQ=</Cell>\n" +
            "<Cell column=\"Y29tbW9uOnc=\" timestamp=\"1445954462132\">MjcuOTI=</Cell>\n" +
            "<Cell column=\"Y29vcmRzOg==\" timestamp=\"1445954701452\">MTEyMjM0</Cell>\n" +
            "<Cell column=\"Y29vcmRzOjE=\" timestamp=\"1445954647780\">MjcuOTI=</Cell>\n" +
            "<Cell column=\"Y29vcmRzOmNlbnRlcg==\" timestamp=\"1445954290236\">Mi4wMw==</Cell>\n" +
            " </Row>\n";


    public static void main(String[] args) {
        System.out.println((new HBaseSerializerUtils<HBaseTable>(HBaseTable.class)).xmlToTable(testXml));
    }
}
