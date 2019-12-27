<template>
    <div id="itemlist-panel" class="itemlist-panel">
        <div class="black-panel"> &nbsp;</div>
        <div class="condition-panel">
                <el-form ref="form" :model="form" label-width="120px">
                	<el-row :gutter="24">
                        <el-col :span="7">
                            <el-form-item label="父节点" prop="pid">
                                <el-input v-model="form.pid" size="small"></el-input>
                            </el-form-item>
                        </el-col>	
                        <el-col :span="7">
                            <el-form-item label="字典值" prop="typeCode">
                                <el-input v-model="form.typeCode" size="small"></el-input>
                            </el-form-item>
                        </el-col>	
                        <el-col :span="7">
                            <el-form-item label="字点名" prop="typeName">
                                <el-input v-model="form.typeName" size="small"></el-input>
                            </el-form-item>
                        </el-col>	
                	</el-row>
                	<div v-if="queryMore">
                	<el-row :gutter="24">
                        <el-col :span="7">
                        	<el-form-item label="状态值" prop="status">
	                            <el-select v-model="form.status" placeholder="请选择" style="width:100%" size="small">
	                                <el-option
	                                    v-for="it in statusArr"
	                                    :key="it.value"
	                                    :label="it.label"
	                                    :value="it.value">
	                                </el-option>
	                            </el-select>
	                        </el-form-item>
                        </el-col>
                        <el-col :span="7">
                            <el-form-item label="排序" prop="orderNum">
                                <el-input v-model="form.orderNum" size="small"></el-input>
                            </el-form-item>
                        </el-col>	
                        <el-col :span="7">
                            <el-form-item label="备注" prop="remark">
                                <el-input v-model="form.remark" size="small"></el-input>
                            </el-form-item>
                        </el-col>	
                	</el-row>
                	<el-row :gutter="24">
                		<el-col :span="7">
                            <el-form-item label="创建时间" prop="createTime">
				                <el-date-picker v-model="form.createTime" type="date" placeholder="选择日期" size="small"></el-date-picker>
				            </el-form-item>
                        </el-col>
                		<el-col :span="7">
                            <el-form-item label="修改时间" prop="updateTime">
				                <el-date-picker v-model="form.updateTime" type="date" placeholder="选择日期" size="small"></el-date-picker>
				            </el-form-item>
                        </el-col>
                	</el-row>
 					</div>
                    <el-row justify="center">
                        <el-col :span="1" :offset="7">
                            <el-button type="primary" icon="el-icon-search" @click="queryList" size="small">查 询</el-button>
                        </el-col>
                        <el-col :span="1" :offset="1">
                            <el-button plain icon="el-icon-switch-button" size="small" @click="resetForm">重 置</el-button>
                        </el-col>
                        <el-col :span="1" :offset="1">
                            <el-button type="text" icon="queryMore==false?'el-icon-arrow-down':'el-icon-arrow-up'" size="small" @click="queryMore==true?queryMore=false:queryMore=true">更多查询</el-button>
                        </el-col>
                    </el-row>
                </el-form>
        </div>

        <div class="list-panel">
            <div class="list-button-p">
                <el-row>
		          <el-col :span="1">
		            <el-button type="primary" icon="el-icon-search" @click="add" size="small">新增</el-button>
		          </el-col>
		          <el-col :span="1" :offset="1">
		            <el-button type="primary" icon="el-icon-s-custom" @click="del" size="small">删除</el-button>
		          </el-col>
		        </el-row>
            </div>
            <div class="list-list">
                <el-table border style="width: 100%" :data="tableData" ref="multipleTable">
                        <el-table-column fixed type="selection" width="40"></el-table-column>
                        <el-table-column prop="pid" label="父节点"></el-table-column>
                        <el-table-column prop="typeCode" label="字典值"></el-table-column>
                        <el-table-column prop="typeName" label="字点名"></el-table-column>
                        <el-table-column prop="status" label="状态值"></el-table-column>
                        <el-table-column prop="orderNum" label="排序"></el-table-column>
                        <el-table-column prop="remark" label="备注"></el-table-column>
                        <el-table-column prop="createTime" label="创建时间"></el-table-column>
                        <el-table-column prop="updateTime" label="修改时间"></el-table-column>
                        <el-table-column fixed="right" label="操作" width="160">
				            <template slot-scope="scope">
				              <el-button @click="update(scope.row)" type="warning" size="mini">编辑</el-button>
				              <el-button @click="detail(scope.row)" type="primary" size="mini">查看</el-button>
				            </template>
				        </el-table-column>
                </el-table>
            </div>
            <div class="list-page">
                <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page.sync="page.currPage"
                    :page-sizes="[5,10,20,30,40]"
                    :page-size="page.pageSize"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="page.totalCount">
                </el-pagination>
            </div>    
        </div>
        <div class="black-panel"> &nbsp;</div>
        <div class="popdialog-container">
            <el-dialog 
                :title="dialogTitle"
                width="60%"
                v-if='dialogVisible'
                :visible.sync="dialogVisible"
                :close-on-click-modal="false"
                :before-close="handleClose">
                <sys-dict-type :checkrow="checkRow" @closechild="closechild"></sys-dict-type>
            </el-dialog>
        </div>
    </div>
</template>
<script>
import sysDictType from "@/components/page/tool/DICT/sysDictTypeManager"
import {DICTApi} from "@/service/sys-api";
export default {
	name:'sysDictType',
    data(){
        return {
            dialogVisible:false,
            visible:false,
            form:{
            	pid:'',
            	typeCode:'',
            	typeName:'',
            	status:'',
            	orderNum:'',
            	remark:'',
            	createTime:'',
            	updateTime:'',
            },
            statusArr:[],
            page:{
            	pageSize:5,
            	currPage:1,
            	totalPage:0
            },
            tableData: [],
            checkRow:{}, //选择的条数
            queryMore:false,  //查询更多
            dialogTitle:'新增'
        }
    },
    components:{
        'sys-dict-type' : sysDictType
    },
    mounted(){
        this.queryList();
    },
    methods:{
    	//改变每页条数
        handleSizeChange(val) {
            this.page.pageSize = val;
            this.queryList();
        },
        //改变当前页面
        handleCurrentChange(val) {
            this.page.currPage = val;
            this.queryList();
        },
        //关闭弹出窗口
        handleClose(done) {
            this.dialogVisible = false;
        },
        //关闭子窗口
        closechild(){
        	this.dialogVisible = false;
        	//this.queryList();
        },
        //重置
        resetForm(){
        	this.$refs['form'].resetFields();
        },
        //查询list数据
        queryList() {
	        let param = Object.assign({}, this.form);
	        param.size = this.page.pageSize;
	        param.page = this.page.currPage;
	        
	        let loading = this.$loading({lock:true,text:'处理中....',background:'rgba(0,0,0,0.5)',target: document.querySelector('.list-panel')});
	        DICTApi.list(param).then((res)=>{
                if(res.code == "0"){
                  this.tableData = res.data.list;
                  this.page.pageSize = res.data.pageSize;
                  this.page.totalCount = res.data.total;
                }else{
                  this.$message({showClose:true,message:'程序出现异常，请联系管理员处理'});
                }
                loading.close();
	        });
	    },
	    //新增
	    add() {
	      this.checkRow.selArr = this.$refs.multipleTable.selection;
	      this.dialogVisible = true;
	      this.checkRow.type='add';
	      this.dialogTitle = "新增";
	    },
	    //修改
	    update(row) {
	      this.checkRow.selArr = row;
	      this.checkRow.type='update';
	      this.dialogVisible = true;
	      this.dialogTitle = "修改";
	    },
	    //查看详情
	    detail(row){
	      this.checkRow.selArr = row;
	      this.checkRow.type='detail';
	      this.dialogVisible = true;
	      this.dialogTitle = "查看详情";
	    },
	    //删除
	    del(){
	      this.checkRow.type='del';
	      let ll = this.$refs.multipleTable.selection;
	      if(ll.length<1){
	          this.$message({ showClose: true, type: 'warning',  message: '请选择删除的行！', duration:2000 });
	      }
	      let idArr = ll.map((e,i)=>{
	        return e.id;
	      });
	      let param = {
	        ids:idArr.join(",")
	      }
	
	      DICTApi.deleteByIds(param).then((res)=>{
                if(res.code == "0"){
                  this.$message({
                    showClose: true,
                    message: '删除成功！',
                    duration:2000
                  });
                  this.queryList();//关闭后刷新界面
                }else{
                  this.$message({showClose:true,message:'删除失败，程序出现异常，请联系管理员处理'});
                }
	        });
	    },
	    
    }
}
</script>
<style lang="scss" scoped>
@import 'src/components/page/style/normal'; 
</style>

