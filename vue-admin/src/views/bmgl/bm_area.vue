<template>
	<section>
		<el-row>
            <el-col :span="12"  class='fullheight pro'>
            	<el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
					<el-form :inline="true">
						<el-form-item prop='name' label='省份'>
							<el-input placeholder="" width='100' v-model='searchkeypro'></el-input>
						</el-form-item>
						<el-form-item>
							<el-button type="primary" @click='searchpro'>查询</el-button>
							<el-button type="primary" @click='handleAdd(0)'>新增</el-button>
						</el-form-item>
					</el-form>
				</el-col>
				<my-el-table :table="datapro" @onGetCurrentRow='prochange' ></my-el-table>
            </el-col>
            <el-col :span="12" class='fullheight'>
				<div class='halfheight city'>
					<el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
						<el-form :inline="true">
							<el-form-item prop='name' label='市'>
								<el-input placeholder="" width='100' v-model='searchkeycity'></el-input>
							</el-form-item>
							<el-form-item>
								<el-button type="primary" @click='searchcity'>查询</el-button>
								<el-button type="primary" @click='handleAdd(1)'>新增</el-button>
							</el-form-item>
						</el-form>
					</el-col>
					<my-el-table :table="datacity" @onGetCurrentRow='citychange'></my-el-table>
				</div>
				<div class='halfheight area'>
					<el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
						<el-form :inline="true">
							<el-form-item prop='name' label='区'>
								<el-input placeholder="" width='100' v-model='searchkeyarea'></el-input>
							</el-form-item>
							<el-form-item>
								<el-button type="primary" @click='searcharea'>查询</el-button>
								<el-button type="primary" @click='handleAdd(2)'>新增</el-button>
							</el-form-item>
						</el-form>
					</el-col>
					<my-el-table :table="dataarea" @onGetCurrentRow='areachange'></my-el-table>
				</div>
            </el-col>
		</el-row>
		</my-el-table>
		<my-new-area :showData="editData" @OnSure='editSure' v-loading='diaLoading'></my-new-area>
	</section>
</template>

<script>
	import { findArea,createArea} from '../../api/api'
	import util from '../../common/js/util'

	
	export default{
		data(){
			return {
				editData:{
					type:0,
					dialogVisible:false,
					form:{
						name:''
					},
					rules:{
						name:[
							{ required: true, message: '名称不能为空', trigger: 'blur' }
						]
					}
				},
				diaLoading:false,
				searchkeypro:'',
				pid:'',
				searchkeycity:'',
				cid:'',
				searchkeyarea:'',
				datapro:{
					loading:false,
					radiocheck:0,
					radiotype:true,
					notHasIndex:false,
					hasOperation:true,
					maxHeight:600,
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
					{label:'省份',prop:'name'}
					],
					data:[]
				},
				datacity:{
					loading:false,
					notHasIndex:false,
					hasOperation:true,
					radiocheck:0,
					radiotype:true,
					maxHeight:600,
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
					{label:'省份',prop:'name'}
					],
					data:[]
				},
				dataarea:{
					loading:false,
					radiocheck:0,
					radiotype:true,
					notHasIndex:false,
					hasOperation:true,
					maxHeight:600,
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
					{label:'省份',prop:'name'}
					],
					data:[]
				}
			}
		},
		methods:{
			editSure(valid,back){
				if(!valid)return false;
				let $this=this;
				let para={
					type:this.editData.type,
					name:this.editData.name,
					pid:this.pid,
					cid:this.cid
				}
				$this.diaLoading=true;
				createArea(para).then(data => {
					$this.diaLoading=false;
					if(!data.success){
						$this.$message.error(data.msg);
						return false;
					}
					back();
					$this.$message({
						message:'新增成功',
						type:'success'
					});
					let nameArr=['pro','city','area'];
					$this.editData.dialogVisible=false;
					$this.editData.form.name='';
					$this['data'+nameArr[para.type]].radiocheck=0;
					if(para.type==0){
						$this.searchpro();
					}
					if(para.type==1){
						$this.searchcity();
					}
					if(para.type==2){
						$this.searcharea();
					}
					
				});;
			},
			handleAdd(flag){
				this.editData.type=flag;
				this.editData.dialogVisible=true;
			},
			prochange(index,row){
				this.pid=this.datapro.data[index].code;
				this.searchcity();
			},
			citychange(index,row){
				this.cid=this.datacity.data[index].code;
				this.searcharea();
			},
			areachange(index,row){
			},
			searchpro(){
				let $this=this;
				let para={
					type:0,
					searchkey:$this.searchkeypro
				}
				$this.searchkeycity='';
				$this.searchkeyarea='';
				$this.datapro.loading=true;
				findArea(para).then(data => {
					$this.datapro.loading=false;
					if(!data.success){
						$this.$message.error(data.msg);
						return false;
					}
					$this.datapro.data=data.data;
					$this.datapro.radiocheck=0;
					$this.pid=data.data.length?data.data[0].code:'';
					$this.searchcity();
				});
			},
			searchcity(){
				let $this=this;
				let para={
					type:1,
					searchkey:$this.searchkeycity,
					pid:$this.pid
				}
				$this.searchkeyarea='';
				$this.datacity.loading=true;
				findArea(para).then(data => {
					$this.datacity.loading=false;
					if(!data.success){
						$this.$message.error(data.msg);
						return false;
					}
					$this.datacity.data=data.data;
					$this.datacity.radiocheck=0;
					$this.cid=data.data.length?data.data[0].code:'';
					$this.searcharea();
				});
			},
			searcharea(){
				let $this=this;
				let para={
					type:2,
					searchkey:$this.searchkeyarea,
					pid:$this.pid,
					cid:$this.cid
				}
				$this.dataarea.loading=true;
				findArea(para).then(data => {
					$this.dataarea.loading=false;
					if(!data.success){
						$this.$message.error(data.msg);
						return false;
					}
					$this.dataarea.data=data.data;
					$this.dataarea.radiocheck=0;
				});
			}
		},
		mounted(){
		this.datapro.maxHeight=util.heightCalc('.pro',['.pro .toolbar',40]);
		this.datacity.maxHeight=util.heightCalc('.city',['.city .toolbar',20]);
		this.dataarea.maxHeight=util.heightCalc('.area',['.area .toolbar',40]);
		this.searchpro();
		}
	}
</script>

<style scoped lang="scss">
	.el-row{
		position: absolute;
		height:calc(100% - 40px);
		width:calc(100% - 250px);
		top:40px;
		left:250px;
	}
	.fullheight{
		height:100%;
	}
	.halfheight{
		height:50%;
	}
	
</style>