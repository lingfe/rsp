<template>
	<section>
		<el-form :inline="true" :model="filter">
			<el-form-item prop='name' label='科室名称'>
				<el-input v-model="filter.ks_name" placeholder=""></el-input>
			</el-form-item>
			 <el-form-item label="科室属性" prop='department_coding_id'>
			    <el-select v-model="filter.department_coding_id">
			      <el-option v-for='(item,i) in coding_nameArr' :label="item.coding_name" :key='item.id' :value="item.id"></el-option>
			    </el-select>
			  </el-form-item>
			<el-form-item>
				<el-button type="primary" v-on:click="getBms">查询</el-button>
				<el-button type="primary" @click="handleAdd">新增</el-button>
			</el-form-item>
		</el-form>
	<el-row class='my-form-item'>
				<el-col :span='6'>
					<el-tree :data="tree" :props="defaultProps" @node-click="handleNodeClick" :default-expand-all='true' highlight-current></el-tree>
				</el-col>
				<el-col :span='18'>
					<my-el-table 
		ref="tableclick" 
		@handleDelete='handleDelete'
		@handleUp='handleUp'
		@handleDown='handleDown'
		:table="dataTable"  >
	<template slot-scope="props" slot="ks_name">
        <a class="template-username" :rguid='props.obj.row.id' href='javascript:void(0);' @click='EditEach(props.obj.$index,props.obj.row)'>{{ props.obj.row.ks_name }}</a>
	</template>
	<template slot-scope="props" slot="stop_flag">
        <span href='javascript:void(0);'>{{ props.obj.row.stop_flag=='1'?'启用中':'停用中'}}</span>
	</template>
	</my-el-table>
				</el-col>
	</el-row>
	<my-new-depart  :showData="editData" @OnSure='editSure'></my-new-depart>
	</section>
</template>

<script>
	import { getBmPersonDe,getBmPersonDeG,createNewBmDeG,editBmDeG,deleteBmDeG,editBmUpDeG,editBmDownDeG} from '../../api/api'
	import utils from '../../common/js/util.js'
	
	export default{
		data(){
			return{
				diaLoading:false,
				filter:{
					ks_name:'',
					department_coding_id:''
				},
				selectDe:'',  //选中的科室id，默认是空即全部
				first:false,
				sels:[],
				tree:[],
				allData:[],
				defaultProps:{
					children: 'children',
          			label: 'ks_name'
				},
				coding_nameArr:[],
				dataTable:{
					loading:false,
					hasSelect:true,
					notHasIndex:false,
					hasOperation:true,
					operation: {             
		              label: '操作',                
			              width: '200',               
			              className: '',               
			              data: [
			              	{
			                  label: '上移',                
			                  Fun: 'handleUp',         
			                  id: '3'                   
			               },
			               {
			                  label: '下移',                
			                  Fun: 'handleDown',         
			                  id: '4'                   
			               },
			                {
			                  label: '删除',                
			                  Fun: 'handleDelete',         
			                  id: '2'                   
			                }
		              ]
		            },
		            tr:[
					{label:'科室属性',prop:'coding_name',width:120
				},
					{label:'科室名称',prop:'ks_name',width:120,show:'template'},
					{label:'电话',prop:'ks_tel',width:140},
					{label:'负责人',prop:'ks_fzr',width:180},
					{label:'备注',prop:'remark',width:180},
					{label:'创建人',prop:'crt_name',width:120},
					{label:'创建时间',prop:'crt_date',width:180},
					{label:'启用',prop:'stop_flag',width:120,show:'template'}
					],
					data:[]
				},
				editData:{
					type:0,
					dialogVisible:false,
					selectId:'',
					freshFlag:0 ,
					pid:''
				}
			}
		},
		methods:{
			 handleNodeClick(data) {
			 	this.selectDe=data.id;
			 	this.editData.pid=data.id;
			 	this.getBms();
		     },
			getBms() {
				var $this=this;
				let para = Object.assign({
					pageIndex: 1,
					pageNum:999,
					pid:$this.selectDe
				},$this.filter);
				this.dataTable.loading = true;
				getBmPersonDeG(para).then((res) => {
					$this.dataTable.loading = false;
					let {state,msg,data}=res;
					if(state!=200){
						$this.$message({
						message:msg||'查询失败',
						type:'error'
					});
					this.dataTable.data=[];
					if(!$this.first)$this.tree=[];
					}else{
						this.dataTable.data = res.data.list;
						this.allData=res.data.list;
						if(!$this.first){
							$this.tree = utils.translateDataToTree(res.data.list,'pid','id');
							$this.first=true;
						}
					}
				}).catch(e => {
					$this.dataTable.loading = false;
					$this.$message({
						message:e,
						type:'error'
					});
				});
			},
			handleDelete(index,row){
				var $this=this;
				$this.$confirm('确定删除该科室信息么？','操作提示',{type:'warning'}).then((a) => {
					deleteBmDeG({id:row.id}).then(res => {
						let {msg,state}=res;
						if(state!=200){
							$this.$message({
								message:msg,
								type:'error'
							});
							return false;
						}
						$this.$message({
				          message: '删除成功',
				          type: 'success'
				       });
						$this.getBms();
					}).catch(e => {
						$this.$message({
							message:e,
							type:'error'
						});
					});
				});
			},
			handleUp(index,row){
				this.dataTable.loading=true;
				editBmUpDeG({id:row.id}).then(res => {
					this.dataTable.loading=false;
					let {state,msg}=res;
					if(state!=200){
							this.$message({
								message:msg,
								type:'error'
							});
							return false;
						}
					this.$message({
			          message: '操作成功',
			          type: 'success'
			        });
					this.getBms();
				}).catch(e => {
					this.$message({
						message:e,
						type:'error'
					});
				});
			},
			handleDown(index,row){
				this.dataTable.loading=true;
				editBmDownDeG({id:row.id}).then(res => {
					this.dataTable.loading=false;
					let {state,msg}=res;
					if(state!=200){
							this.$message({
								message:msg,
								type:'error'
							});
							return false;
						}
					this.$message({
			          message: '操作成功',
			          type: 'success'
			        });
					this.getBms();
				}).catch(e => {
					this.$message({
						message:e,
						type:'error'
					});
				});
			},
			handleAdd(){
				this.editData.type=0;
				this.editData.freshFlag=0;
				this.editData.form={};
				this.editData.dialogVisible=true;
			},
			editSure(valid){
				if(valid)return this.getBms();
			},
			EditEach(index,row){
				this.editData.type=1;
				this.editData.form=Object.assign({},row,{stop_flag:!row.stop_flag,is_walkthrough:!row.is_walkthrough});
				this.sels=row;
				this.editData.freshFlag=0;
				this.editData.dialogVisible=true;
			}
		},
		mounted(){
			this.getBms();
			let para={
				stop_flag:1,
				pageIndex:1,
				pageNum:999
			}
			getBmPersonDe(para).then((res) => {
					let {state,msg,data}=res;
					if(state!=200){
						$this.$message({
						message:msg||'查询失败',
						type:'error'
					});
					this.dataTable.data=[];
					}else
					this.coding_nameArr= [{id:'',coding_name:'全部'}].concat(res.data.list);
				});
		}	
	}
</script>

<style>
</style>