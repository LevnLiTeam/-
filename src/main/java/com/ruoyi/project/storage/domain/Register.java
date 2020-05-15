package com.ruoyi.project.storage.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * @author :lihao
 * @date :2020/04/30
 * @description :客户实体类
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "客户对象", description = "客户对象")
public class Register extends BaseEntity {

    /**
     * 序列化id
     */
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    private Long userId;

    /**
     * 用户账号
     */
    @ApiModelProperty(value = "用户账号")
    private String userName;

    /**
     * 用户姓名
     */
    @ApiModelProperty(value = "用户姓名")
    private String nickName;

    /**
     * 用户类型（00系统用户；01后台端用户；02手机端用户）
     */
    @ApiModelProperty(value = "用户类型（00系统用户；01后台端用户；02手机端用户）")
    private String userType;

    /**
     * 用户邮箱
     */
    @ApiModelProperty(value = "用户邮箱")
    private String email;

    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码")
    private String phonenumber;

    /**
     * 用户性别（0男 1女 2未知）
     */
    @ApiModelProperty(value = "用户性别（0男 1女 2未知）")
    private String sex;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 帐号状态（0正常 1停用）
     */
    @ApiModelProperty(value = "帐号状态（0正常 1停用）")
    private String status;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @ApiModelProperty(value = "删除标志（0代表存在 2代表删除）")
    private String delFlag;

    /**
     * 创建者
     */
    @ApiModelProperty(value = "创建者")
    private String createBy;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 当前积分
     */
    @ApiModelProperty(value = "当前积分")
    private Long currentPoints;

    /**
     * 版本号
     */
    @ApiModelProperty(value = "版本号")
    private Long version;
}
