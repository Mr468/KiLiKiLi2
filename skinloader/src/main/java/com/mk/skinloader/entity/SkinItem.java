package com.mk.skinloader.entity;

import android.view.View;

import com.mk.skinloader.attr.SkinAttr;
import com.mk.skinloader.utils.ListUtils;

import java.util.ArrayList;
import java.util.List;

public class SkinItem {
	
	public View view;
	
	public List<SkinAttr> attrs;
	
	public SkinItem(){
		attrs = new ArrayList<>();
	}
	
	public void apply(){
		if(ListUtils.isEmpty(attrs)){
			return;
		}
		for(SkinAttr at : attrs){
			at.apply(view);
		}
	}
	
	public void clean(){
		if(ListUtils.isEmpty(attrs)){
			return;
		}
		for(SkinAttr at : attrs){
			at = null;
		}
	}

	@Override
	public String toString() {
		return "SkinItem [view=" + view.getClass().getSimpleName() + ", attrs=" + attrs + "]";
	}
}
