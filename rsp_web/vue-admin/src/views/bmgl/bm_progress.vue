<template>
	<section>
		<el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
		<el-form :inline="true" :model="filter">
			<!--<router-link :to="{ path: '/bm_progress',query:{user_id:123}}">asdf</router-link>-->
			<!--<router-link :to="{ name: '流程编码',params:{user_id:123}}">name</router-link>-->
			<!--<el-button @click='jump'></el-button>-->
			<el-form-item label='编码类型'>
				<my-radio :radiodata='types' @onRadioChange='typeChange'></my-radio>
			</el-form-item>
			<br>
			<el-form-item prop='name' label='编码名称'>
				<el-input v-model="filter.name" placeholder=""></el-input>
			</el-form-item>
			 <el-form-item label="状态" prop='status'>
			    <el-select v-model="filter.status">
			      <el-option label="所有" value=""></el-option>
			      <el-option label="在用" value="1"></el-option>
			      <el-option label="停用" value="0"></el-option>
			    </el-select>
			  </el-form-item>
			<el-form-item>
				<el-button type="primary" v-on:click="getBms">查询</el-button>
				<el-button type="primary" @click="handleAdd">新增</el-button>
			</el-form-item>
		</el-form>
	</el-col>
	<my-el-table 
		ref="tableclick" 
		@handleDelete='handleDelete'
		@handleUp='handleUp'
		@handleDown='handleDown'
		:table="dataTable"  >
	<template slot-scope="props" slot="coding_name">
        <a class="template-username" :rguid='props.obj.row.id' href='javascript:void(0);' @click='EditEach(props.obj.$index,props.obj.row)'>{{ props.obj.row.coding_name }}</a>
	</template>
	<template slot-scope="props" slot="state">
        <span href='javascript:void(0);'>{{ props.obj.row.state=='1'?'启用中':'停用中'}}</span>
	</template>
	</my-el-table>
	<my-new-bm :showData="editData" @OnSure='editSure' v-loading='diaLoading'></my-new-bm>
	</section>
</template>

<script>
	import { getUserListPage ,getBmPerson,createNewBm,editBm,deleteBm} from '../../api/api'
	
	export default{
		data(){
			return{
				diaLoading:false,
				filter:{
					name:'',
					status:'',
					type:'8',
				},
				sels:[],
				types:{
					checked:'8',
					type:1,
					data:[{text:'流程类型',id:'8'},{text:'流程分类',id:'9'}],
//					data:[{text:'流程类型',id:'0'},{text:'流程分类',id:'1'}],
					textfield:'text',
					valuefield:'id'
				},
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
					{label:'编码名称',prop:'coding_name',width:120,show:'template'
				},
					{label:'备注',prop:'remark','minWidth':120},
					{label:'创建人',prop:'creator_name',width:120},
					{label:'创建时间',prop:'cdate',width:180},
					{label:'停用状态',prop:'state',width:120,show:'template'},
					{label:'停用人',prop:'modify_name',width:120},
					{label:'停用说明',prop:'stop_explain','minWidth':120},
					{label:'停用时间',prop:'mdate',width:180},
					],
					data:[]
				},
				editData:{
					type:0,
					dialogVisible:false,
					resize:'none',
					form:{
						name:'',
						bz:'',
						flag:0,
						tysm:''
					},
					rules:{
						name:[
							{ required: true, message: '编码名称不能为空', trigger: 'blur' }
						]
					}
				}
			}
		},
		methods:{
			getBms() {
				var $this=this;
				let para = {
					pageIndex: 1,
					pageNum:999,
					coding_name:this.filter.name,
					state:this.filter.status=='所有'?'':this.filter.status,
					coding_type:this.filter.type
				};
				this.dataTable.loading = true;
				getBmPerson(para).then((res) => {
					$this.dataTable.loading = false;
					let {state,msg,data}=res;
					if(state!=200){
						$this.$message({
						message:msg||'查询失败',
						type:'error'
					});
					this.dataTable.data=[];
					}else
					this.dataTable.data = res.data.list;
				});
//				getUserListPage(para).then((res) => {
//					this.dataTable.data = res.data.users;
//					this.dataTable.loading = false;
//				});
			},
			jump(){
				this.$router.push({path:'/bm_progress',query:{a:1}});
			},
			clearEditForm(){
				this.editData.form={
					name:'',
					bz:'',
					flag:0,
					tysm:''
				};
			},
			handleDelete(index,row){
				var $this=this;
				$this.$confirm('确定删除该条编码么？','操作提示',{type:'warning'}).then((a) => {
					deleteBm().then(data => {
						if(!data.success){
							$this.$message.error(data.msg);
							return false;
						}
						$this.$message({
				          message: '删除成功',
				          type: 'success'
				        });
						$this.getBms();
					});
				});
			},
			handleUp(index,row){
				this.dataTable.loading=true;
				editBm({upflag:0}).then(data => {
					this.dataTable.loading=false;
					if(!data.success){
							this.$message.error(data.msg);
							return false;
						}
					this.$message({
			          message: '操作成功',
			          type: 'success'
			        });
					this.getBms();
				});
			},
			handleDown(index,row){
				this.dataTable.loading=true;
				editBm({upflag:1}).then(data => {
					this.dataTable.loading=false;
					if(!data.success){
							this.$message.error(data.msg);
							return false;
						}
					this.$message({
			          message: '操作成功',
			          type: 'success'
			        });
					this.getBms();
				});
			},
			handleAdd(){
				this.editData.type=0;
				this.clearEditForm();
				this.editData.dialogVisible=true;
			},
			editSure(valid,back){
				var $this=this;
				if(!valid)return false;
				$this.diaLoading=true;
				if($this.editData.type==0){
					let para={
						coding_name:this.editData.form.name,
						remark:this.editData.form.bz,
						coding_type:this.filter.type
					}
					createNewBm(para).then(res => {
						$this.diaLoading=false;
						let {state,msg}=res;
						if(state!=200){
							$this.$message.error(msg);
							return false;
						}
						back();
						$this.$message({
						message:'新增成功',
						type:'success'
					});
					$this.editData.dialogVisible=false;
					$this.getBms();
					});
					return false;
				}
				$this.$confirm('确定这样修改').then(() => {
					let selected=this.sels;
					let para={
						id:selected.id,
						coding_type:selected.coding_type,
						coding_name:this.editData.form.name,
						remark:this.editData.form.bz,
						state:this.editData.form.flag?0:1,
						stop_explain:this.editData.form.tysm
					}
					editBm(para).then(res => {
						let {state,msg,data}=res;
						$this.diaLoading=false;
						if(state!=200)return $this.$message.error(msg);
						back();
						$this.$message({
						message:'修改成功',
						type:'success'
					});
					$this.editData.dialogVisible=false;
					$this.getBms();
					});
					return false;
				});
			},
			EditEach(index,row){
				this.editData.type=1;
				this.editData.form={
					name:row.coding_name,
					bz:row.remark,
					flag:!row.state,
					tysm:''
				};
				this.sels=row;
				this.editData.dialogVisible=true;
			},
			typeChange(val){
				this.filter.type=val;
				this.filter.status='';
				this.getBms();
			}
		},
		mounted(){
			this.getBms();
		}	
	}
</script>

<style>
</style>