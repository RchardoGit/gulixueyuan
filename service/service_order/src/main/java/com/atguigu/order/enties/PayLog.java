package com.atguigu.order.enties;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 支付日志表
 * </p>
 *
 * @author kly
 * @since 2022-01-27
 */
@Getter
@Setter
@TableName("t_pay_log")
@ApiModel(value = "PayLog对象", description = "支付日志表")
public class PayLog extends Model<PayLog> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty("订单号")
    @TableField("order_no")
    private String orderNo;

    @ApiModelProperty("支付完成时间")
    @TableField("pay_time")
    private Date payTime;

    @ApiModelProperty("支付金额（分）")
    @TableField("total_fee")
    private BigDecimal totalFee;

    @ApiModelProperty("交易流水号")
    @TableField("transaction_id")
    private String transactionId;

    @ApiModelProperty("交易状态")
    @TableField("trade_state")
    private String tradeState;

    @ApiModelProperty("支付类型（1：微信 2：支付宝）")
    @TableField("pay_type")
    private Integer payType;

    @ApiModelProperty("其他属性")
    @TableField("attr")
    private String attr;

    @ApiModelProperty("逻辑删除 1（true）已删除， 0（false）未删除")
    @TableField("is_deleted")
    private Boolean isDeleted;

    @ApiModelProperty("创建时间")
    @TableField("gmt_create")
    private Date gmtCreate;

    @ApiModelProperty("更新时间")
    @TableField("gmt_modified")
    private Date gmtModified;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
