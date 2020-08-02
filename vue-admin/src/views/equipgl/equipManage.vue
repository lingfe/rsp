<template>
	<section>
		<el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
		<el-form :inline="true" :model="filter">
			<el-form-item prop='sb_name' label='设备名称'>
				<el-input v-model="filter.sb_name" placeholder=""></el-input>
			</el-form-item>
			<el-form-item prop='sb_syks_id' label='使用科室'>
				<el-select v-model="filter.sb_syks_id">
			      <el-option v-for='(item,i) in ksArr' :label="item.ks_name" :key='item.id' :value="item.id"></el-option>
			 </el-select>
			</el-form-item>
			 <el-form-item label="放射诊疗许可证" prop='flag_fszlxkz'>
			    <el-select v-model="filter.flag_fszlxkz">
			      <el-option label="所有" value=""></el-option>
			      <el-option label="有" value="1"></el-option>
			      <el-option label="无" value="0"></el-option>
			    </el-select>
			  </el-form-item>
			  <el-form-item label="辐射安全许可证" prop='flag_fsaqxkz'>
			    <el-select v-model="filter.flag_fsaqxkz">
			      <el-option label="所有" value=""></el-option>
			      <el-option label="有" value="1"></el-option>
			      <el-option label="无" value="0"></el-option>
			    </el-select>
			  </el-form-item>
			  <el-form-item label="大型配置许可证" prop='flag_dxpzxkz'>
			    <el-select v-model="filter.flag_dxpzxkz">
			      <el-option label="所有" value=""></el-option>
			      <el-option label="有" value="1"></el-option>
			      <el-option label="无" value="0"></el-option>
			    </el-select>
			  </el-form-item>
			  <el-form-item label="设备状态" prop='flag_sb_state'>
			    <el-select v-model="filter.flag_sb_state">
			      <el-option label="正常" value="0"></el-option>
			      <el-option label="作废" value="1"></el-option>
			      <el-option label="报废" value="2"></el-option>
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
		:table="dataTable"  >
	<template slot-scope="props" slot="sb_name">
        <a class="template-username" :rguid='props.obj.row.id' href='javascript:void(0);' @click='EditEach(props.obj.$index,props.obj.row)'>{{ props.obj.row.sb_name }}</a>
	</template>
	<template slot-scope="props" slot="flag_fszlxkz">
        <span href='javascript:void(0);'>{{ props.obj.row.flag_fszlxkz=='1'?'有':'无'}}</span>
	</template>
	<template slot-scope="props" slot="flag_fsaqxkz">
        <span href='javascript:void(0);'>{{ props.obj.row.flag_fsaqxkz=='1'?'有':'无'}}</span>
	</template>
	<template slot-scope="props" slot="flag_dxpzxkz">
        <span href='javascript:void(0);'>{{ props.obj.row.flag_dxpzxkz=='1'?'有':'无'}}</span>
	</template>
	<template slot-scope="props" slot="is_scrap">
        <span href='javascript:void(0);'>{{ props.obj.row.is_scrap=='1'?'报废':'有效'}}</span>
	</template>
	<template slot-scope="props" slot="is_walkthrough">
        <span href='javascript:void(0);'>{{ props.obj.row.is_walkthrough=='1'?'需预排':'不需预排'}}</span>
	</template>
	</my-el-table>
	<my-new-equip :showData="editData" @OnSure='editSure'></my-new-equip>
	</section>
</template>

<script>
	import { getEquipList ,getBmPerson,getBmPersonDeG,deleteEquip} from '../../api/api'
	
	export default{
		data(){
			return{
				filter:{
					sb_name:'',
					sb_syks_id:'',
					flag_fszlxkz:'',
					flag_fsaqxkz:'',
					flag_dxpzxkz:'',
					flag_sb_state:'0'
				},
				sels:[],
				ksArr:[],
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
					{label:'设备名称',prop:'sb_name',width:140,show:'template'
				},
					{label:'规格型号',prop:'sb_ggxh',width:140},
					{label:'设备编号',prop:'sb_code',width:140},
					{label:'设备类别',prop:'sb_zzlb_name',width:120},
					{label:'生产厂家',prop:'sb_manfacturer_name',width:120},
					{label:'使用科室',prop:'sb_syks_name',width:120},
					{label:'所在场所',prop:'sb_szcs',width:180},
					{label:'用途',prop:'sb_yt_id',width:180},
					{label:'来源',prop:'sb_ly',width:120},
					{label:'额定电压（kv）',prop:'sb_eddy',width:140},
					{label:'放射诊疗许可证',prop:'flag_fszlxkz',width:140,show:'template'},
					{label:'上证时间',prop:'fszlxkz_date',width:180},
					{label:'状态',prop:'fszlxkz_state_name',width:120},
					{label:'辐射安全许可证',prop:'flag_fsaqxkz',width:140,show:'template'},
					{label:'上证时间',prop:'fsaqxkz_date',width:180},
					{label:'状态',prop:'fsaqxkz_state_name',width:120},
					{label:'大型配置许可证',prop:'flag_dxpzxkz',width:140,show:'template'},
					{label:'上证时间',prop:'dxpzxkz_date',width:180},
					{label:'状态',prop:'dxpzxkz_state_name',width:180},
					{label:'去向',prop:'sb_qx',width:120},
					{label:'报废',prop:'is_scrap',width:120,show:'template'},
					{label:'报废时间',prop:'scrap_date',width:180},
					{label:'创建人',prop:'crt_name',width:120},
					{label:'创建时间',prop:'crt_date',width:180}
					],
					data:[]
				},
				editData:{
					type:0,
					dialogVisible:false,
					selectId:'',
					freshFlag:0 ,
					tab:'first'
				}
			}
		},
		methods:{
			getBms() {
				var $this=this;
				let para =Object.assign({pageIndex:1,pageNum:999},$this.filter);
				this.dataTable.loading = true;
				getEquipList(para).then((res) => {
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
					$this.$message({
						message:e||'查询失败',
						type:'error'
					});
					$this.dataTable.loading = false;
				});
			},
			getksArr(){
		 	let para={
		 		pageIndex:1,
		 		pageNum:999,
		 		stop_flag:1
		 	};
		 	let $this=this;
		 	getBmPersonDeG(para).then(res => {
				$this.ksArr= res.data.list||[];	
				$this.ksArr.unshift({ks_name:'所有',id:''});
		 	}).catch(e => {
		 		$this.ksArr=[];
				$this.ksArr.unshift({ks_name:'所有',id:''});
		 	});
		 },
			handleAdd(){
				this.editData.type=0;
				this.editData.freshFlag=0;
				this.editData.form={};
				this.editData.openFlag=true;
				this.editData.dialogVisible=true;
				this.editData.tab='first';
			},
			handleDelete(index,row){
				var $this=this;
				$this.$confirm('确定删除这条设备信息么？','操作提示',{type:'warning'}).then((a) => {
					deleteEquip({id:row.id}).then(res => {
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
			editSure(valid){
				if(valid)return this.getBms();
			},
			EditEach(index,row){
				this.editData.type=1;
				this.editData.openFlag=true;
				this.editData.form=Object.assign({},row,{flag_fszlxkz:!!row.flag_fszlxkz,flag_fsaqxkz:!!row.flag_fsaqxkz,flag_dxpzxkz:!!row.flag_dxpzxkz});
				this.sels=row;
				this.editData.freshFlag=0;
				this.editData.dialogVisible=true;
				this.editData.tab='first';
			}
		},
		mounted(){
			this.getBms();
			this.getksArr();
		}	
	}
</script>

<style>
</style>