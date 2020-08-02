<template>
	<section>
		<el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
		<el-form :inline="true" :model="filter">
			<el-form-item prop='name' label='生产厂家'>
				<el-input v-model="filter.name" placeholder=""></el-input>
			</el-form-item>
			 <!--<el-form-item label="状态" prop='state'>
			    <el-select v-model="filter.state">
			      <el-option label="所有" value=""></el-option>
			      <el-option label="在用" value="1"></el-option>
			      <el-option label="停用" value="0"></el-option>
			    </el-select>
			  </el-form-item>
			<el-form-item>-->
				<el-button type="primary" v-on:click="getBms">查询</el-button>
				<el-button type="primary" @click="handleAdd">新增</el-button>
			</el-form-item>
		</el-form>
	</el-col>
	<my-el-table 
		ref="tableclick" 
		@handleDelete='handleDelete'
		:table="dataTable"  >
		
		<!--@handleUp='handleUp'-->
		<!--@handleDown='handleDown'-->

	<template slot-scope="props" slot="name">
        <a class="template-username" :rguid='props.obj.row.id' href='javascript:void(0);' @click='EditEach(props.obj.$index,props.obj.row)'>{{ props.obj.row.name }}</a>
	</template>
	</my-el-table>
	<el-dialog  :title="calcTitle()" :visible.sync="dialogVisible" :close-on-click-modal='false'>
      <el-form ref="form" :model="form" label-width="80px" :rules='rules'>
        <el-form-item label="生产厂家" prop='name'>
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="备注" prop='remark'>
          <el-input v-model="form.remark" type="textarea"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="cancel">取 消</el-button>
        <el-button type="primary" @click="sure" :loading='btnLoading'>确 定</el-button>
      </span>
    </el-dialog>
	</section>
</template>

<script>
	import { getBmPersonPr,createNewBmPr,editBmPr,deleteBmPr,editBmUpPr,editBmDownPr} from '../../api/api'
	
	export default{
		data(){
			return{
				diaLoading:false,
				btnLoading:false,
				dialogVisible:false,
				type:0, //0新增，1编辑
				filter:{
					name:'',
					state:''
				},
				sels:[],
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
//			              	{
//			                  label: '上移',                
//			                  Fun: 'handleUp',         
//			                  id: '3'                   
//			               },
//			               {
//			                  label: '下移',                
//			                  Fun: 'handleDown',         
//			                  id: '4'                   
//			               },
			                {
			                  label: '删除',                
			                  Fun: 'handleDelete',         
			                  id: '2'                   
			                }
		              ]
		            },
		            tr:[
					{label:'编码名称',prop:'name',width:120,show:'template'
				},
					{label:'备注',prop:'remark','minWidth':120},
					{label:'创建人',prop:'creator_name',width:120},
					{label:'创建时间',prop:'cdate',width:180}
					],
					data:[]
				},
				rules:{
					name:[{required:true,message:'生产厂家名称不能为空',trigger:'blur'}]
				},
				form:{
					name:'',
					remark:''
				}
			}
		},
		methods:{
			getBms() {
				var $this=this;
				let para = $this.filter;
				this.dataTable.loading = true;
				getBmPersonPr(para).then((res) => {
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
						message:e,
						type:'error'
					});
				});
			},
			handleDelete(index,row){
				var $this=this;
				$this.$confirm('确定删除这条厂家信息么？','操作提示',{type:'warning'}).then((a) => {
					deleteBmPr({id:row.id}).then(res => {
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
			calcTitle(){
				let $this=this;
				if($this.type==0)return '新增生产厂家';
				return '编辑生产厂家信息';
			},
			handleUp(index,row){
				this.dataTable.loading=true;
				editBmUp({id:row.id}).then(res => {
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
				editBmDown({id:row.id}).then(res => {
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
				let $this=this;
				$this.type=0;
				$this.dialogVisible=true;
				$this.form.name='';
				$this.form.remark='';
			},
			sure(){
				var $this=this;
				this.$refs.form.validate((valid) => {
				if(!valid)return false;
				if($this.type==0){
					$this.btnLoading=true;
					let para=$this.form;
					createNewBmPr(para).then(res => {
						$this.btnLoading=false;
						let {state,msg}=res;
						if(state!=200){
							$this.$message.error(msg);
							return false;
						}
						$this.dialogVisible=false;
						this.$emit('OnSure',true);
						$this.$message({
						message:'新增成功',
						type:'success'
					});
					$this.getBms();
					}).catch(() => {
						$this.btnLoading=false;
					});
					return false;
				}
				$this.$confirm('确定这样修改').then(() => {
					$this.btnLoading=true;
					let para=Object.assign({id:$this.sels.id},$this.form);
					editBmPr(para).then(res => {
						let {state,msg,data}=res;
						$this.btnLoading=false;
						if(state!=200)return $this.$message.error(msg);
						$this.dialogVisible=false;
						$this.$message({
							message:'修改成功',
							type:'success'
						});
					$this.getBms();
					}).catch(e => {});
					return false;
				});
				});
			},
			EditEach(index,row){
				let $this=this;
				this.sels=row;
				$this.type=1;
				$this.dialogVisible=true;
				$this.form.name=row.name;
				$this.form.remark=row.remark;
			},
			cancel(){
				
			}
		},
		mounted(){
			this.dataTable.tr.forEach(d => {if(d.belong=='yp')d.show=true;if(d.prop=='is_walkthrough')d.show='template';});
			this.getBms();
		}	
	}
</script>

<style>
</style>