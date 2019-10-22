package com.elling.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.shiro.util.StringUtils;

import com.elling.sys.model.SysRole;
import com.elling.sys.model.SysRoleMenu;

public class Test {
	public static void main(String[] args) {
		
		String ids = "'aa','bb','cc'";
		String[] idsarr = ids.split(",");
		List list = Arrays.asList(idsarr);
		for(int i=0;i<list.size();i++) {
			System.out.println(i+":"+list.get(i));
		}
//		List<String> list = new ArrayList<String>();
//		list.add("csdkf.jsp");
//		list.add("dfsfasldriewure.png");
//		list.add("abcdfsd.psd");
//		System.out.println(StringUtils.join(list.iterator(), ","));
		
//		SysRole sysRole = new SysRole();
//		sysRole.setRoleName("test009");
//		
//		SysRoleMenu sr = new SysRoleMenu();
//		sr.setMenuId(1L);
//		
//		SysRoleMenu sr1 = new SysRoleMenu();
//		sr1.setMenuId(2L);
//		
//		List<SysRoleMenu> list = new ArrayList<SysRoleMenu>();
//		list.add(sr);
//		list.add(sr1);
//		
//		sysRole.setMenus(list);
//		
//		Long roleId = 23L;
//		sysRole.getMenus().forEach(item->{
//			item.setRoleId(roleId);
//		});
//		
//		sysRole.getMenus().forEach(item->{
//			System.out.println("roleId"+item.getRoleId());
//			System.out.println("menuId"+item.getMenuId());
//		});
	}
}
