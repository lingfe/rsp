<template>
	<section>
		<el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
		<el-form :inline="true" :model="filter">
			<el-form-item prop='equipment_name' label='设备名称'>
				<el-input v-model="filter.equipment_name" placeholder=""></el-input>
			</el-form-item>
			<el-form-item prop='department' label='使用科室'>
				<el-input v-model="filter.department" placeholder=""></el-input>
			</el-form-item>
			 <el-form-item label="放射诊疗许可证" prop='is_obtain_rdtl'>
			    <el-select v-model="filter.is_obtain_rdtl">
			      <el-option label="所有" value=""></el-option>
			      <el-option label="有" value="1"></el-option>
			      <el-option label="无" value="0"></el-option>
			    </el-select>
			  </el-form-item>
			  <el-form-item label="辐射安全许可证" prop='is_obtain_rsl'>
			    <el-select v-model="filter.is_obtain_rsl">
			      <el-option label="所有" value=""></el-option>
			      <el-option label="有" value="1"></el-option>
			      <el-option label="无" value="0"></el-option>
			    </el-select>
			  </el-form-item>
			  <el-form-item label="大型配置许可证" prop='is_obtain_lcl'>
			    <el-select v-model="filter.is_obtain_lcl">
			      <el-option label="所有" value=""></el-option>
			      <el-option label="有" value="1"></el-option>
			      <el-option label="无" value="0"></el-option>
			    </el-select>
			  </el-form-item>
			  <el-form-item label="设备状态" prop='state'>
			    <el-select v-model="filter.state">
			      <el-option label="所有" value="0"></el-option>
			      <el-option label="有效" value="1"></el-option>
			      <el-option label="报废" value="2"></el-option>
			      <el-option label="删除" value="3"></el-option>
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
		:table="dataTable"  >
	<template slot-scope="props" slot="equipment_name">
        <a class="template-username" :rguid='props.obj.row.id' href='javascript:void(0);' @click='EditEach(props.obj.$index,props.obj.row)'>{{ props.obj.row.equipment_name }}</a>
	</template>
	<template slot-scope="props" slot="is_obtain_rdtl">
        <span href='javascript:void(0);'>{{ props.obj.row.is_obtain_rdtl=='1'?'有':'无'}}</span>
	</template>
	<template slot-scope="props" slot="is_obtain_rsl">
        <span href='javascript:void(0);'>{{ props.obj.row.is_obtain_rdtl=='1'?'有':'无'}}</span>
	</template>
	<template slot-scope="props" slot="is_obtain_lcl">
        <span href='javascript:void(0);'>{{ props.obj.row.is_obtain_rdtl=='1'?'有':'无'}}</span>
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
	import { getEquipList ,getBmPerson} from '../../api/api'
	
	export default{
		data(){
			return{
				filter:{
					equipment_name:'',
					department:'',
					is_obtain_rdtl:'',
					is_obtain_rsl:'',
					is_obtain_lcl:'',
					state:''
				},
				sels:[],
				dataTable:{
					loading:false,
					hasSelect:true,
					notHasIndex:false,
					hasOperation:false,
		            tr:[
					{label:'设备名称',prop:'equipment_name',width:140,show:'template'
				},
					{label:'规格型号',prop:'specification_type',width:140},
					{label:'设备编号',prop:'equipment_number',width:140},
					{label:'设备类别',prop:'lb',width:120},
					{label:'生产厂家',prop:'cj',width:120},
					{label:'使用科室',prop:'ks',width:120},
					{label:'所在场所',prop:'location',width:180},
					{label:'用途',prop:'purpose',width:180},
					{label:'来源',prop:'source',width:120},
					{label:'额定电压（kv）',prop:'rated_voltage_kv',width:140},
					{label:'放射诊疗许可证',prop:'is_obtain_rdtl',width:140,show:'template'},
					{label:'上证时间',prop:'obtain_rdtl_date',width:180},
					{label:'状态',prop:'zt',width:120},
					{label:'辐射安全许可证',prop:'is_obtain_rsl',width:140,show:'template'},
					{label:'上证时间',prop:'obtain_rsl_date',width:180},
					{label:'状态',prop:'zt',width:120},
					{label:'大型配置许可证',prop:'is_obtain_lcl',width:140,show:'template'},
					{label:'上证时间',prop:'obtain_lcl_date',width:180},
					{label:'状态',prop:'zt',width:180},
					{label:'去向',prop:'whereabouts',width:120},
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
			handleAdd(){
				this.editData.type=0;
				this.editData.freshFlag=0;
				this.editData.form={};
				this.editData.dialogVisible=true;
				this.editData.tab='first';
			},
			editSure(valid){
				if(valid)return this.getBms();
			},
			EditEach(index,row){
				this.editData.type=1;
				this.editData.form=Object.assign({},row,{is_obtain_rdtl:!!row.is_obtain_rdtl,is_obtain_rsl:!!row.is_obtain_rsl,is_obtain_lcl:!!row.is_obtain_lcl,is_scrap:!!row.is_scrap});
				this.sels=row;
				this.editData.freshFlag=0;
				this.editData.dialogVisible=true;
				this.editData.tab='first';
			}
		},
		mounted(){
			this.getBms();
		}	
	}
</script>

<style>
</style>