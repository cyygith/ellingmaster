<!DOCTYPE html>
<html>
<head>
	<title>房屋押金单</title>
</head>
<style>
.rentReceipt-panel{
    background-color: rgb(240, 235, 235);
}
table{
	    width: 98%;
	    height:98%;
	    text-align: center;
	    border-color:#b2b2b2;
	    border-collapse:collapse;
}
td{
    padding:7px 0 7px 0;
}
.c-title{
    text-align: center;
    font-size: 24px;
}
.c-title{

}
.cc-no{
    color: red;
    font-size: 16px;
}
.c-info{
    display: flex;
}
.cc-time{
    margin-left: auto;
    padding-right: 1rem;
}
.c-foot{
    display: flex;
}
.cc-customer{
    margin-left: auto;
    padding-right: 1rem;
}
.recepitPanel{
    padding: 1rem;
}
</style>
<body>
	<div class="rentReceipt-panel" id="receiptPanel">
		<div class="content-panel recepitPanel">
	            <div class="c-title">
	                <div class="cc-title">房租押金收据</div>
	                <div class="cc-no">No.${CodeNum!}</div>
	            </div>
	            <div class="c-info">
	                <table style="margin-bottom:4px;">
	            		<tr>
	            			<td width="65%" style="text-align:left;">租客及房号：${rentContract.groupName!}</td>
	            			<td width="35%" style="text-align:right;padding-right:5px;">${startTime!}</td>
	            		</tr>
	            	</table>
	            </div>
	            <div>
	                <table border="1" style="font-weight:normal;width:100%;overflow:auto;margin:auto;">
	                    <tr>
	                        <td>
	                            <p style="text-align:left;">
	                                &nbsp;&nbsp;&nbsp;&nbsp;
						                                今收到<sapn class="cc_underline">${rentContract.personName!}</sapn>租期从${rentContract.startTime!}到${rentContract.endTime!}，
						                                房屋租金人民币（大写）${bigRenterSum!}（￥：${rentContract.renter!}）,
						                                交来押金人民币（大写）${bigdepositSum!}（￥：${rentContract.deposit!}）,
						                                房屋坐落于${houseAddress!}
						                                备注：${rentContract.remark!}
	                            </p>
	                        </td>
	                    </tr>
	                </table>
	            </div>
	            <div class="c-foot">
	            	<table style="margin-top:3px;">
	            		<tr>
	            			<td width="60%" style="text-align:left;">租客签名:</td>
	            			<td width="40%">收款人签名:</td>
	            		</tr>
	            	</table>
	            </div>
	            
	    </div>
	</div>
</body>
</html>