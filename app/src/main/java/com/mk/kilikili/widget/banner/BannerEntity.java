package com.mk.kilikili.widget.banner;

/**
 * Banner模型类
 */
public class BannerEntity {

  public BannerEntity(String link, String title, String img) {

    this.link = link;
    this.title = title;
    this.img = img;
  }


  public String title;

  public String img;

  public String link;
}
