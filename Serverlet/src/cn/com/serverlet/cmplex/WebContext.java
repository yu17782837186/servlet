package cn.com.serverlet.cmplex;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebContext {
//    private List<Entity> entities = null;
//    private List<Mapping> mappings = null;
//    //key-->servelet-name  value-->servelet-class
//    private Map<String,String> entityMap = new HashMap<>();
//    //key-->url-pattern  value-->servelet-name;
//    private Map<String,String> mappingMap = new HashMap<>();
//
//    public WebContext(List<Entity> entities, List<Mapping> mappings) {
//        this.entities = entities;
//        this.mappings = mappings;
//        //将entity的List转成了map
//        for (Entity entity:entities) {
//            entityMap.put(entity.getName(),entity.getClz());
//        }
//        //将map的List转成了对应的map
//        for (Mapping mapping:mappings) {
//            for (String pattern:mapping.getPatterns()) {
//                mappingMap.put(pattern,mapping.getName());
//            }
//        }
//    }
//    public String getClz(String pattern) {
//        String name = mappingMap.get(pattern);
//        //通过URL的路径找到了对应的class
//        return entityMap.get(name);
//    }
private List<Entity> entitys  =null;
    private List<Mapping> mappings =null;

    //key-->servlet-name  value -->servlet-class
    private Map<String,String> entityMap =new HashMap<String,String>();
    //key -->url-pattern value -->servlet-name
    private Map<String,String> mappingMap =new HashMap<String,String>();
    public WebContext(List<Entity> entitys, List<Mapping> mappings) {
        this.entitys = entitys;
        this.mappings = mappings;

        //将entity 的List转成了对应map
        for(Entity entity:entitys) {
            entityMap.put(entity.getName(), entity.getClz());
        }
        //将map 的List转成了对应map
        for(Mapping mapping:mappings) {
            for(String pattern: mapping.getPatterns()) {
                mappingMap.put(pattern, mapping.getName());
            }
        }
    }
    /**
     * 通过URL的路径找到了对应class
     * @param pattern
     * @return
     */
    public String getClz(String pattern) {
        String name = mappingMap.get(pattern);
        return entityMap.get(name);
    }
}
