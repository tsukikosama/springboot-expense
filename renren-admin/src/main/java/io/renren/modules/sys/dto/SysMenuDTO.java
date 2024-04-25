/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.modules.sys.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.renren.common.utils.TreeNode;
import io.renren.common.validator.group.AddGroup;
import io.renren.common.validator.group.DefaultGroup;
import io.renren.common.validator.group.UpdateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;
import java.util.Date;

/**
 * 菜单管理
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
@Schema(title = "菜单管理")
public class SysMenuDTO extends TreeNode<SysMenuDTO> implements Serializable {
    private static final long serialVersionUID = 1L;

	@Schema(title = "id")
	@Null(message="{id.null}", groups = AddGroup.class)
	@NotNull(message="{id.require}", groups = UpdateGroup.class)
	private Long id;

	@Schema(title = "上级ID")
	@NotNull(message="{sysmenu.pid.require}", groups = DefaultGroup.class)
	private Long pid;

	@Schema(title = "菜单名称")
	@NotBlank(message="{sysmenu.title.require}", groups = DefaultGroup.class)
	private String title;

	@Schema(title = "菜单URL")
	private String url;

	@Schema(title = "类型  0：菜单   1：按钮")
	@Range(min=0, max=1, message = "{sysmenu.type.range}", groups = DefaultGroup.class)
	private Integer menuType;

	@Schema(title = "菜单图标")
	private String icon;

	@Schema(title = "授权(多个用逗号分隔，如：sys:user:list,sys:user:save)")
	private String permissions;

	@Schema(title = "排序")
	@Min(value = 0, message = "{sort.number}", groups = DefaultGroup.class)
	private Integer sort;

	@Schema(title = "创建时间")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Date createDate;

	@Schema(title = "上级菜单名称")
	private String parenttitle;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Long getPid() {
		return pid;
	}

	@Override
	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String gettitle() {
		return title;
	}

	public void settitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getMenuType() {
		return menuType;
	}

	public void setMenuType(Integer menuType) {
		this.menuType = menuType;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getParenttitle() {
		return parenttitle;
	}

	public void setParenttitle(String parenttitle) {
		this.parenttitle = parenttitle;
	}
}
