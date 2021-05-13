package com.rainpavilion.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class Menu implements Serializable {
    /**
     * 后天展示列表
     *
     * CREATE TABLE `cmfz_menu` (
     *   `menu_id` int(11) NOT NULL AUTO_INCREMENT,
     *   `menu_name` varchar(50) DEFAULT NULL,
     *   `menu_url` varchar(500) DEFAULT NULL,
     *   `menu_parent_id` int(11) DEFAULT NULL,
     *   PRIMARY KEY (`menu_id`)
     * )
     *
     */

    private int menuId;
    private String menuName;
    private String menuUrl;
    private int menuParentId;
    private List<Menu> child;

    @Override
    public String toString() {
        return "Menu{" +
                "menuId=" + menuId +
                ", menuName='" + menuName + '\'' +
                ", menuUrl='" + menuUrl + '\'' +
                ", menuParentId=" + menuParentId +
                ", child=" + child +
                '}';
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public int getMenuParentId() {
        return menuParentId;
    }

    public void setMenuParentId(int menuParentId) {
        this.menuParentId = menuParentId;
    }

    public List<Menu> getChild() {
        return child;
    }

    public void setChild(List<Menu> child) {
        this.child = child;
    }

    public Menu() {
    }

    public Menu(int menuId, String menuName, String menuUrl, int menuParentId, List<Menu> child) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.menuUrl = menuUrl;
        this.menuParentId = menuParentId;
        this.child = child;
    }
}
