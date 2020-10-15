<!DOCTYPE html>
<html>
<head>
	<title>房屋收据</title>
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
    
}
.c-title{
    text-align: center;
    font-size: 24px;
}
.c-title{

}
.cc-no{
    color: red;
    font-size: 12px;
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
	                <div class="cc-title">出租屋专用收说</div>
	                <div class="cc-no">No.${CodeNum!}</div>
	            </div>
	            <div class="c-info">
	                <table style="margin-bottom:10px;">
	            		<tr>
	            			<td width="60%" style="text-align:left;">租客及房号：${rentBill.groupName!}${rentBill.houseName!}:</td>
	            			<td width="40%">${startTime}</td>
	            		</tr>
	            	</table>
	            </div>
	            <div>
	                <table border="1" style="font-weight:normal;width:100%;overflow:auto;margin:auto;">
	                    <tr>
	                        <td rowspan="2" width="10%">项目</td>
	                        <td rowspan="2" width="12%">上月</td>
	                        <td rowspan="2" width="12%">本月</td>
	                        <td rowspan="2" width="13%">实用</td>
	                        <td rowspan="2" width="13%">单价</td>
	                        <td colspan="6" width="20%">金额</td>
	                        <td rowspan="7"  width="20%">备注</td>
	                    </tr>
	                    <tr>
	                        <td>1万</td>
	                        <td>1仟</td>
	                        <td> 佰</td>
	                        <td>h十</td>
	                        <td>个元</td>
	                        <td> 角</td>
	                    </tr>
	                    <tr>
	                        <td>电费( 度 )</td>
	                        <td>${rentBill.lastElectric!}</td>
	                        <td>${rentBill.currElectric!}</td>
	                        <td>${eleFee!}</td>
	                        <td>1 元/度 </td>
	                        <td></td>
	                        <td></td>
	                        <td></td>
	                        <td></td>
	                        <td></td>
	                        <td></td>
	                    </tr>
	                    <tr>
	                        <td>水费(吨)</td>
	                        <td>${rentBill.lastWater!}</td>
	                        <td>${rentBill.currWater!}</td>
	                        <td>${waterFee}（${rentBill.rentNum!}人）</td>
	                        <td>${waterPayTypeName}</td>
	                        <td></td>
	                        <td></td>
	                        <td></td>
	                        <td></td>
	                        <td></td>
	                        <td></td>
	                    </tr>
	                    <tr>
	                        <td>房租</td>
	                        <td>${rentBill.money!}</td>
	                        <td colspan="3">${rentBill.startTime!}到${rentBill.endTime!}</td>
	                        <td></td>
	                        <td></td>
	                        <td></td>
	                        <td></td>
	                        <td></td>
	                        <td></td>
	                    </tr>
	                    <tr>
	                        <td>押金</td>
	                        <td></td>
	                        <td colspan="2">网费</td>
	                        <td></td>
	                        <td></td>
	                        <td></td>
	                        <td></td>
	                        <td></td>
	                        <td></td>
	                        <td></td>
	                    </tr>
	                    <tr>
	                        <td>卫生费</td>
	                        <td></td>
	                        <td colspan="2">其他费用</td>
	                        <td></td>
	                        <td></td>
	                        <td></td>
	                        <td></td>
	                        <td></td>
	                        <td></td>
	                        <td></td>
	                    </tr>
	                    <tr>
	                        <td colspan="12" style="text-align: left;">
	                           	 合计金额(大写)    ￥ ${rentBill.sum!}
	                        </td>
	                    </tr>
	                </table>
	            </div>
	            <div class="c-foot">
	            	<table style="margin-top:10px;">
	            		<tr>
	            			<td width="60%" style="text-align:left;">开票人:</td>
	            			<td width="40%">客户签字:</td>
	            		</tr>
	            	</table>
	            </div>
	            
	    </div>
	</div>
</body>
</html>