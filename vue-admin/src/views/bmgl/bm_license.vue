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
	<template slot-scope="props" slot="stop_flag">
        <span href='javascript:void(0);'>{{ props.obj.row.stop_flag=='1'?'启用中':'停用中'}}</span>
	</template>
	<template slot-scope="props" slot="flag_yp">
        <span href='javascript:void(0);'>{{ props.obj.row.flag_yp=='1'?'需预排':'不需预排'}}</span>
	</template>
	</my-el-table>
	<my-new-bm :showData="editData" @OnSure='editSure' v-loading='diaLoading'></my-new-bm>
	</section>
</template>

<script>
	import { getUserListPage ,getBmPerson,createNewBm,editBm,deleteBm,editBmUp,editBmDown} from '../../api/api'
	
	export default{
		data(){
			return{
				diaLoading:false,
				filter:{
					name:'',
					status:'',
					type:'40001',
				},
				sels:[],
				types:{
					checked:'40001',
					type:1,
					data:[{text:'附件类别',id:'40001'}],
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
					{label:'创建人',prop:'crt_name',width:120},
					{label:'创建时间',prop:'crt_date',width:180},
					{label:'是否预排',prop:'flag_yp',width:100,show:false,belong:'yp'},
					{label:'预排名称',prop:'yp_name',width:120,show:false,belong:'yp'},
					{label:'预排周期',prop:'yp_cycle',width:100,show:false,belong:'yp'},
					{label:'停用状态',prop:'stop_flag',width:120,show:'template'},
					{label:'停用人',prop:'stop_name',width:120},
					{label:'停用说明',prop:'stop_explain','minWidth':120},
					{label:'停用时间',prop:'stop_date',width:180},
					],
					data:[]
				},
				editData:{
					type:0,
					dialogVisible:false,
					coding_type:'40001'
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
					stop_flag:this.filter.status=='所有'?'':this.filter.status,
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
				}).catch(e => {
					$this.dataTable.loading = false;
					$this.$message({
						message:msg||'查询失败',
						type:'error'
					});
				});
			},
			jump(){
				this.$router.push({path:'/bm_progress',query:{a:1}});
			},
			handleDelete(index,row){
				var $this=this;
				$this.$confirm('确定删除该条编码么？','操作提示',{type:'warning'}).then((a) => {
					deleteBm({id:row.id}).then(res => {
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
					}).catch(() => {
						
					});
				});
			},
			handleUp(index,row){
				this.dataTable.loading=true;
				editBmUp({id:row.id,coding_type:this.filter.type}).then(res => {
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
				});
			},
			handleDown(index,row){
				this.dataTable.loading=true;
				editBmDown({id:row.id,coding_type:this.filter.type}).then(res => {
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
				this.editData.form=Object.assign({},row,{stop_flag:!row.stop_flag,flag_yp:!!row.flag_yp});
				this.sels=row;
				this.editData.freshFlag=0;
				this.editData.dialogVisible=true;
			},
			typeChange(val){
				this.filter.type=val;
				this.filter.status='';
				this.editData.coding_type=val;
				if(val==40001)this.dataTable.tr.forEach(d => {if(d.belong=='yp')d.show=true;if(d.prop=='flag_yp')d.show='template';});
				else this.dataTable.tr.forEach(d => {if(d.belong=='yp')d.show=false;});
				this.getBms();
			}
		},
		mounted(){
			this.getBms();
			if(this.filter.type==40001)this.dataTable.tr.forEach(d => {if(d.belong=='yp')d.show=true;if(d.prop=='flag_yp')d.show='template';});
			else this.dataTable.tr.forEach(d => {if(d.belong=='yp')d.show=false;});
		}	
	}
</script>

<style>
</style>