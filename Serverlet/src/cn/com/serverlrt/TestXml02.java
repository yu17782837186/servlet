package cn.com.serverlrt;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class TestXml02 {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        //1 获取解析工厂
        SAXParserFactory factory = SAXParserFactory.newInstance();
        //2 从解析工厂获取解析器
        SAXParser parse = factory.newSAXParser();
        //3 编写处理器

        //4 加载文档Document注册处理器
        PersionHandler handler = new PersionHandler();
        //5 解析
        parse.parse(Thread.currentThread().getContextClassLoader().
                getResourceAsStream("cn/com/serverlet/p.xml"),handler);
        //获取数据
        List<Persion> persions = handler.getPersions();
//        System.out.println(persions.size());
        for (Persion p : persions) {
            System.out.println(p.getName()+"-->"+p.getAge());
        }
    }
}

class PersionHandler extends DefaultHandler {
    private List<Persion> persions;
    private Persion persion;
    private String tag;//存储操作的标签
    @Override
    public void startDocument() throws SAXException {
        persions = new ArrayList<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.println(qName+"-->解析开始");
        if (qName != null) {
            tag = qName;//存储标签名
            if (qName.equals("persion")) {
                persion = new Persion();
            }
        }

    }
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String contents = new String(ch,start,length).trim();
//        if (contents.length() > 0) {
//            System.out.println("内容为->"+contents);
//        }else {
//            System.out.println("内容为->"+"空");
//        }
        if (tag != null) {//处理了空的问题
            if (tag.equals("name")) {
                persion.setName(contents);
            }else if (tag.equals("age")) {
                if (contents.length() > 0) {
                    persion.setAge(Integer.valueOf(contents));
                }
            }
        }


    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println(qName+"-->解析结束");
        if (qName != null) {
            if (qName.equals("persion")) {
                persions.add(persion);
            }
        }
        tag = null;//tag 丢弃掉
    }

    public List<Persion> getPersions() {
        return persions;
    }



    @Override
    public void endDocument() throws SAXException {

    }
}
