package hello.servlet.web.frontcontroller;

import java.util.HashMap;
import java.util.Map;


public class ModelView {
    private String viewPath;
    private Map<String, Object> model = new HashMap<>();

    public ModelView(String viewPath) {
        this.viewPath = viewPath;
    }

    public String getViewPath() {
        return viewPath;
    }

    public void setViewPath(String viewPath) {
        this.viewPath = viewPath;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }
}
