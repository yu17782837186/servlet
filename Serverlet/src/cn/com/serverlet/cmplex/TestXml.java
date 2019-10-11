package cn.com.serverlet.cmplex;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class TestXml {
    public static void main(String[] args) throws Exception    {
//        SAXParserFactory factory = SAXParserFactory.newInstance();
////        //2 从解析工厂获取解析器
////        SAXParser parse = factory.newSAXParser();
////        //3 编写处理器
////
////        //4 加载文档Document注册处理器
////        WebHandler handler = new WebHandler();
////        //5 解析
////        parse.parse(Thread.currentThread().getContextClassLoader().
////                getResourceAsStream("cn/com/serverlet/cmplex/web.xml"),handler);
////        //获取数据
////        WebContext context = new WebContext(handler.getEntities(),handler.getMappings());
//////        List<Entity> entities = handler.getEntities();
//////        List<Mapping> mappings = handler.getMappings();
//////
//////        System.out.println(entities.size());
//////        System.out.println(mappings.size());
////
////        //假设输入/login
////        String name = context.getClz("/g");
////        Class clazz = Class.forName(name);
////        Serverlet serverlet = (Serverlet) clazz.getConstructor().newInstance();
////        System.out.println(serverlet);
////        serverlet.service();
////    }
        //SAX解析
        //1、获取解析工厂
        SAXParserFactory factory=SAXParserFactory.newInstance();
        //2、从解析工厂获取解析器
        SAXParser parse =factory.newSAXParser();
        //3、编写处理器
        //4、加载文档 Document 注册处理器
        WebHandler handler=new WebHandler();
        //5、解析
        parse.parse(Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream("cn/com/serverlet/cmplex/web.xml")
                ,handler);

        //获取数据
        WebContext context = new WebContext(handler.getEntitys(),handler.getMappings());
        //假设你输入了 /login
        String className = context.getClz("/login");
        Class clz =Class.forName(className);
        Serverlet servlet =(Serverlet)clz.getConstructor().newInstance();
        System.out.println(servlet);
        servlet.service();

    }
}
class WebHandler extends DefaultHandler {
//    private List<Entity> entities= new ArrayList<>();
//    private List<Mapping> mappings= new ArrayList<>();
//    private Entity entity;
//    private Mapping mapping;
//    private String tag;//存储操作的标签
//    private boolean isMapping = false;
////    @Override
////    public void startDocument() throws SAXException {
////        entities = new ArrayList<>();
////        mappings = new ArrayList<>();
////    }
//
//    @Override
//    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
//
//        if (qName != null) {
//            tag = qName;//存储标签名
//            if (tag.equals("servlet")) {
//                entity = new Entity();
//                isMapping = false;
//            }else if (tag.equals("servlet-mapping")) {
//                mapping = new Mapping();
//                isMapping = true;
//            }
//        }
//
//    }
//    @Override
//    public void characters(char[] ch, int start, int length) throws SAXException {
//        String contents = new String(ch,start,length).trim();
//        if (tag != null) {//处理了空的问题
//            if (isMapping) {//操作servlet-mapping
//                if (tag.equals("servlet-name")) {
//                    mapping.setName(contents);
//                }else if (tag.equals("url-pattern")) {
//                    mapping.addPattern(contents);
//                }
//            }else {//操作servlet
//                if (tag.equals("servlet-name")) {
//                    entity.setName(contents);
//                }else if (tag.equals("servlet-class")) {
//                    entity.setClz(contents);
//                }
//            }
//        }
//    }
//
//    @Override
//    public void endElement(String uri, String localName, String qName) throws SAXException {
//        if (qName != null) {
//            if (qName.equals("servlet")) {
//                entities.add(entity);
//            }else if (qName.equals("servlet-mapping")) {
//                mappings.add(mapping);
//            }
//        }
//        tag = null;//tag 丢弃掉
//    }
//
//    public List<Entity> getEntities() {
//        return entities;
//    }
//
//
//    public List<Mapping> getMappings() {
//        return mappings;
//    }
private List<Entity> entitys  = new ArrayList<Entity>();
    private List<Mapping> mappings = new ArrayList<Mapping>();
    private Entity entity ;
    private Mapping mapping ;
    private String tag; //存储操作标签
    private boolean isMapping = false;


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(null!=qName) {
            tag = qName; //存储标签名
            if(tag.equals("servlet")) {
                entity = new Entity();
                isMapping = false;
            }else if(tag.equals("servlet-mapping")) {
                mapping = new Mapping();
                isMapping = true;
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String contents = new String(ch,start,length).trim();
        if(null!=tag) { //处理了空
            if(isMapping) { //操作servlet-mapping
                if(tag.equals("servlet-name")) {
                    mapping.setName(contents);
                }else if(tag.equals("url-pattern")) {
                    mapping.addPattern(contents);
                }
            }else { //操作servlet
                if(tag.equals("servlet-name")) {
                    entity.setName(contents);
                }else if(tag.equals("servlet-class")) {
                    entity.setClz(contents);
                }
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(null!=qName) {
            if(qName.equals("servlet")) {
                entitys.add(entity);
            }else if(qName.equals("servlet-mapping")) {
                mappings.add(mapping);
            }
        }
        tag = null; //tag丢弃了
    }

    public List<Entity> getEntitys() {
        return entitys;
    }

    public List<Mapping> getMappings() {
        return mappings;
    }
}