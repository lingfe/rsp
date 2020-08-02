<template>
		<el-dialog :data='showData'  :title="calcTitle()" :visible.sync="showData.dialogVisible" :close-on-click-modal='false' class='equip_dia'>
			<el-tabs v-model="showData.tab" @tab-click="handleClick" >
			    <el-tab-pane label="设备信息" name="first">
			    	<el-form ref="form" :model="form" :inline='true' label-width='100px' :rules='rules'>
			    		<el-row class='my-form-item'>
								<el-col :span='12'>
									<el-form-item label="设备名称" prop='equipment_name'>
										<el-input v-model='form.equipment_name'></el-input>
									</el-form-item>
								</el-col>
								<el-col :span='12'>
									<el-form-item label="规格型号" prop='specification_type'>
										<el-input v-model='form.specification_type'></el-input>
									</el-form-item>
								</el-col>
			    		</el-row>
			    		<el-row class='my-form-item'>
								<el-col :span='12'>
									<el-form-item label="设备编号" prop='equipment_number'>
										<el-input v-model='form.equipment_number'></el-input>
									</el-form-item>
								</el-col>
								<el-col :span='12'>
									<el-form-item label="设备类别" prop='coding_id'>
										<el-select v-model="form.coding_id">
									      <el-option v-for='(item,i) in lbArr' :label="item.coding_name" :key='item.id' :value="item.id"></el-option>
									    </el-select>
									</el-form-item>
								</el-col>
			    		</el-row>
			    		<el-row class='my-form-item'>
								<el-col :span='8'>
									<el-form-item label="额定电压（KV）" prop='rated_voltage_kv'>
										<el-input v-model='form.rated_voltage_kv'></el-input>
									</el-form-item>
								</el-col>
								<el-col :span='8'>
									<el-form-item label="生产厂家" prop='manufacturer_id'>
										<el-select v-model="form.manufacturer_id">
									      <el-option v-for='(item,i) in cjArr' :label="item.name" :key='item.id' :value="item.id"></el-option>
									   </el-select>
									</el-form-item>
								</el-col>
								<el-col :span='8'>
									<el-form-item label="使用科室" prop='department_id'>
										<el-select v-model="form.department_id" @change='departChange'>
									      <el-option v-for='(item,i) in ksArr' :label="item.department_name" :key='item.id' :value="item.id"></el-option>
									  </el-select>
									</el-form-item>
								</el-col>
			    		</el-row>
			    		<el-row class='my-form-item'>
								<el-col :span='12'>
									<el-form-item label="用途" prop='purpose'>
										<el-input v-model='form.purpose' type='textarea' :autosize='{minRows: 1}'></el-input>
									</el-form-item>
								</el-col>
								<el-col :span='12'>
									<el-form-item label="所在场所" prop='location'>
										<el-input v-model='form.location'  type='textarea' :autosize='{minRows: 1}'></el-input>
									</el-form-item>
								</el-col>
			    		</el-row>
			    		<el-row class='my-form-item'>
			    			<el-col :span='6'>
			    				<el-form-item class='my-form-item-check' prop='is_obtain_rdtl'>
									<el-checkbox v-model='form.is_obtain_rdtl'>放射诊疗许可证</el-checkbox>
								</el-form-item>
			    			</el-col>
			    			<el-col :span='12'>
			    				<el-form-item label='状态'>
									<span></span>
								</el-form-item>
			    			</el-col>
			    			<el-col :span='6'>
								<el-form-item label="上证时间" prop='obtain_rdtl_date'>
									<el-date-picker
								      v-model="form.obtain_rdtl_date"
									  :editable='false'
								      type="date"
								      value-format="yyyy-MM-dd"
								      placeholder="">
								    </el-date-picker>
								</el-form-item>
							</el-col>
			    		</el-row>
			    		<el-row class='my-form-item'>
			    			<el-col :span='6'>
			    				<el-form-item class='my-form-item-check' prop='is_obtain_rsl'>
									<el-checkbox v-model='form.is_obtain_rsl'>辐射安全许可证</el-checkbox>
								</el-form-item>
			    			</el-col>
			    			<el-col :span='12'>
			    				<el-form-item label='状态'>
									<span></span>
								</el-form-item>
			    			</el-col>
			    			<el-col :span='6'>
								<el-form-item label="上证时间" prop='obtain_rsl_date'>
									<el-date-picker
								      v-model="form.obtain_rsl_date"
									  :editable='false'
								      type="date"
								      value-format="yyyy-MM-dd"
								      placeholder="">
								   </el-date-picker>
								</el-form-item>
							</el-col>
			    		</el-row>
			    		<el-row class='my-form-item'>
			    			<el-col :span='6'>
			    				<el-form-item class='my-form-item-check' prop='is_obtain_lcl'>
									<el-checkbox v-model='form.is_obtain_lcl'>大型配置许可证</el-checkbox>
								</el-form-item>
			    			</el-col>
			    			<el-col :span='12'>
			    				<el-form-item label='状态'>
									<span></span>
								</el-form-item>
			    			</el-col>
			    			<el-col :span='6'>
								<el-form-item label="上证时间" prop='obtain_lcl_date'>
									<el-date-picker
								      v-model="form.obtain_lcl_date"
									  :editable='false'
								      type="date"
								      value-format="yyyy-MM-dd"
								      placeholder="">
								  </el-date-picker>
								</el-form-item>
							</el-col>
			    		</el-row>
			    		<el-row class='my-form-item'>
								<el-col :span='12'>
									<el-form-item label="来源" prop='source'>
										<el-input v-model='form.source'></el-input>
									</el-form-item>
								</el-col>
								<el-col :span='12'>
									<el-form-item label="去向" prop='whereabouts'>
										<el-input v-model='form.whereabouts'></el-input>
									</el-form-item>
								</el-col>
			    		</el-row>
			    		<el-row class='my-form-item'>
			    			<el-col :span='6'>
			    				<el-form-item class='my-form-item-check' prop='is_scrap'>
									<el-checkbox v-model='form.is_scrap'>报废</el-checkbox>
								</el-form-item>
			    			</el-col>
			    			<el-col :span='6'>
								<el-form-item label="报废时间" prop='scrap_date'>
									<el-date-picker
								      v-model="form.scrap_date"
									  :editable='false'
								      type="date"
								      value-format="yyyy-MM-dd"
								      placeholder="">
								 </el-date-picker>
								</el-form-item>
							</el-col>
			    		</el-row>
			    		<el-row class='my-form-item' v-for='item in ypArr' :key='item.id'>
			    			<el-col :span='6'>
			    				<el-form-item class='my-form-item-check'>
									<el-checkbox v-model='item.check'  >{{item.walkthrough_name}}</el-checkbox>
								</el-form-item>
			    			</el-col>
			    			<el-col :span='6'>
								<el-form-item :label="item.walkthrough_name+'时间'">
									<el-date-picker
									  value-format="yyyy-MM-dd"
									  :editable='false'
								      type="date"
								      placeholder="">
								   </el-date-picker>
								</el-form-item>
							</el-col>
							<el-col :span='6'>
								<el-form-item label="周期">
									<el-input :value='item.walkthrough_cycle'></el-input>
								</el-form-item>
							</el-col>
							<el-col :span='6'>
								<el-form-item label="下次时间">
									<el-date-picker
								      type="date"
									  :editable='false'
									  v-model='item.next_time'
								      value-format="yyyy-MM-dd"
								      placeholder="">
								   </el-date-picker>
								</el-form-item>
							</el-col>
			    		</el-row>
      				</el-form>
			    </el-tab-pane>
			    <el-tab-pane label="资料信息" name="second" v-if='showData.type!=0'>
			    	<el-row class='my-form-item'>
								<el-col :span='6'>
									<el-tree :data="tree" :props="defaultProps" @node-click="handleNodeClick" :default-expand-all='true' highlight-current></el-tree>
								</el-col>
								<el-col :span='18'>
									<my-el-table :table="dataTable"  >
									</my-el-table>
								</el-col>
			    		</el-row>
			    </el-tab-pane>
			    <el-tab-pane label="痕迹信息" name="third" v-if='showData.type!=0'>
			    	<my-timeline :showData='timeline'></my-timeline>
			    </el-tab-pane>
			</el-tabs>
      
      <span slot="footer" class="dialog-footer">
        <el-button @click="cancel">取 消</el-button>
        <el-button type="primary" @click="sure" :loading='btnLoading'>确 定</el-button>
      </span>
    </el-dialog>
</template>

<script>
	import {getBmPerson,getBmPersonYp,getBmPersonPr,getBmPersonDeG,createNewEquip,editEquip} from '../../api/api.js'
	import utils from '../../common/js/util.js'
	export default{
		name:'departDia',
		props:{
			showData:{
				type:Object,
				default(){
					return{
						type:0,
						dialogVisible:false,
						selectId:'',
						freshFlag:0 //初始化页面的flag，每次打开页面都必须传递。
					}
				}
			}
		},
		data(){
			let $this = this;
			 var check_obtain_rdtl_date = (rule, value, callback) => {
		        setTimeout(() => {
		        	if($this.form.is_obtain_rdtl&&!value){
		        		callback(new Error('请选择取得时间'));
		        	}else callback();
		        }, 1000);
		      };
		       var check_obtain_rsl_date = (rule, value, callback) => {
		        setTimeout(() => {
		        	if($this.form.is_obtain_rsl&&!value){
		        		callback(new Error('请选择取得时间'));
		        	}else callback();
		        }, 1000);
		      };
		       var check_obtain_lcl_date = (rule, value, callback) => {
		        setTimeout(() => {
		        	if($this.form.is_obtain_lcl&&!value){
		        		callback(new Error('请选择取得时间'));
		        	}else callback();
		        }, 1000);
		      };
			return {
				activeName:'first',
				dialogVisible:true,
				btnLoading:false,
				form:{
					equipment_name:'',
					specification_type:'',
					equipment_number:'',
					coding_id:'',
					purpose:'',
					location:'',
					source:'',
					scrap_date:'',
					whereabouts:'',
					is_scrap:0,
					rated_voltage_kv:'',
					manufacturer_id:'',
					department_id:'',
					is_obtain_rdtl:0,
					obtain_rdtl_date:'',
					is_obtain_rsl:0,
					obtain_rsl_date:'',
					is_obtain_lcl:0,
					obtain_lcl_date:'',
					department_name:''
				},
				rules:{
					equipment_name:[{ required: true, message: '请输入设备名称', trigger: 'blur' }],
					equipment_number:[{ required: true, message: '请输入设备编号', trigger: 'blur' }],
					obtain_rdtl_date:[{ validator: check_obtain_rdtl_date, trigger: 'blur' }],
					obtain_rsl_date:[{ validator: check_obtain_rsl_date, trigger: 'blur' }],
					obtain_lcl_date:[{ validator: check_obtain_lcl_date, trigger: 'blur' }]
				},
				lbArr:[],
				cjArr:[],
				ksArr:[],
				ypArr:[],
				tree:[{label:'资料信息',children:[
					{label:'资料信息',id:1},
					{label:'设备说明书',id:2},
					{label:'医疗器械注册证',id:3},
					{label:'大型配置许可证',id:4},
					{label:'设备铭牌',id:5},
				]}],
				defaultProps:{
					children: 'children',
          			label: 'label'
				},
				dataTable:{
					loading:false,
					hasSelect:true,
		            tr:[
					{label:'上证时间',prop:'time',width:120},
					{label:'附件类型',prop:'type',width:120},
					{label:'附件名称',prop:'name',minWidth:180}
					],
					data:[
						{time:'2018-01-03',type:'年检报告',name:'2018年检报告.pdf'},
						{time:'2018-01-03',type:'设备说明书',name:'设备说明书.pdf'},
						{time:'2018-01-03',type:'设备铭牌',name:'设备铭牌.pdf'}
					]
				},
				timeline:{
					data:[
						{time:'2018-01-12 12:15:45【张三】',content:'修改设备编号'},
						{time:'2018-01-12 12:15:45【张三】',content:'修改放射许可证的上证时间'},
						{time:'2018-01-12 12:15:45【李四】',content:'新增设备信息'}
					]
				}
			}
		},
		methods:{
			cancel(){},
			sure(){
				let $this=this;
				let dateArr=['obtain_rdtl_date','obtain_rsl_date','obtain_lcl_date','scrap_date'];
				let flagArr=['is_obtain_rdtl','is_obtain_rsl','is_obtain_lcl','is_scrap'];
				this.$refs.form.validate((valid) => {
				if(!valid)return false;
				$this.ypArr.forEach(d => {
					if($this.form.id)d.equipment_id=$this.form.id;
					d.is_walkthrough=d.check?1:0;
					d.next_time=d.next_time?utils.formatDate.format(new Date(d.next_time),'yy-MM-dd'):'';
				});
				let para=Object.assign({},$this.form,{hospital_id:'1001',hospital_name:'初始',eq_list:JSON.stringify({"eq_list":$this.ypArr||$this.form.eq_list||[]})});
				Object.keys(para).forEach((key,i) => {
						if(dateArr.indexOf(key)>-1)para[key]=para[key]?utils.formatDate.format(new Date(para[key]),'yy-MM-dd'):'';
						if(flagArr.indexOf(key)>-1)para[key]=para[key]?1:0;
				});
				console.log(para);
				if($this.showData.type==0){
					$this.btnLoading=true;
					para.ctype='application/json;charset=utf-8';
					createNewEquip(para).then(res => {
						$this.btnLoading=false;
						let {state,msg}=res;
						if(state!=200){
							$this.$message.error(msg);
							return false;
						}
						$this.showData.dialogVisible=false;
						this.$emit('OnSure',true);
						$this.$message({
						message:'新增成功',
						type:'success'
					});
					}).catch(e => {
						$this.btnLoading=false;
						$this.$message.error(e);
					});
					return false;
				}
				$this.$confirm('确定这样修改').then(() => {
					$this.btnLoading=true;
					para.ctype='application/json;charset=utf-8';
					editEquip(para).then(res => {
						let {state,msg,data}=res;
						$this.btnLoading=false;
						if(state!=200)return $this.$message.error(msg);
						$this.showData.dialogVisible=false;
						$this.$message({
							message:'修改成功',
							type:'success'
						});
						this.$emit('OnSure',true);
					}).catch(e => {
						$this.btnLoading=false;
						$this.$message.error(e);
					});
					return false;
				});
				});
			},
			handleClick(tab, event) {
		 },
		 getlbArr(){
		 	let para={
		 		pageIndex:1,
		 		pageNum:999,
		 		stop_flag:1,
		 		coding_type:20001
		 	};
		 	let $this=this;
		 	getBmPerson(para).then(res => {
				$this.lbArr= res.data.list||[];			 		
		 	}).catch(e => {
		 		$this.lbArr=[];
		 	})
		 },
		 getcjArr(){
		 	let para={
		 		pageIndex:1,
		 		pageNum:999,
		 		name:''
		 	};
		 	let $this=this;
		 	getBmPersonPr(para).then(res => {
				$this.cjArr= res.data.list||[];			 		
		 	}).catch(e => {
		 		$this.cjArr=[];
		 	})
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
		 	}).catch(e => {
		 		$this.ksArr=[];
		 	});
		 },
		 getypArr(){
		 	let para={
		 		hospital_id:'',
		 		coding_type:20003,
		 		is_walkthrough:1
		 	}
		 	let $this=this;
		 	getBmPersonYp(para).then(res => {
				$this.ypArr= res.data||[];			 		
		 	}).catch(e => {
		 		$this.ypArr=[];
		 	});
		 },
		    handleNodeClick(data) {
		     },
			calcTitle(){
				let $this=this;
				return $this.showData.type==0?'新增科室':'科室详情编辑';
			},
			departChange(val){
				let selected=this.ksArr.filter(d => d.id==val)[0]||'';
				if(selected) this.form.department_name=selected.department_name;
				else this.form.department_name='';
			}
		},
		mounted(){
			this.getlbArr();
			this.getcjArr();
			this.getksArr();
			this.getypArr();
		},
		beforeUpdate(){
			this.activeName='first';
			if(!this.showData.freshFlag){
				if(this.$refs.form)this.$refs.form.resetFields();
				this.form=Object.assign({
					equipment_name:'',
					specification_type:'',
					equipment_number:'',
					coding_id:'',
					purpose:'',
					location:'',
					scrap_date:'',
					source:'',
					whereabouts:'',
					is_scrap:0,
					rated_voltage_kv:'',
					manufacturer_id:'',
					department_id:'',
					is_obtain_rdtl:0,
					obtain_rdtl_date:'',
					is_obtain_rsl:0,
					obtain_rsl_date:'',
					is_obtain_lcl:0,
					obtain_lcl_date:'',
					department_name:''
				},this.showData.form||{});
				this.showData.freshFlag=1;
			}
		}
	}
</script>

<style>
	
.equip_dia .el-dialog__body{
		padding:10px 20px;
	}
	.equip_dia .el-dialog{
		width:1000px;
	}
	.equip_dia .my-form-item-check .el-form-item__content{
		margin-left: 75px;
	}
	.equip_dia .my-form-item-check .el-checkbox{
		text-align: left;
	}
	
	.el-timeline {
    margin: 0;
    font-size: 14px;
    list-style: none;
}
.el-timeline-item {
    position: relative;
    padding-bottom: 20px;
}
.el-timeline-item__wrapper {
    position: relative;
    padding-left: 28px;
    top: -3px;
}
.el-timeline-item__content {
    color: #303133;
}
.el-timeline-item__timestamp{
	word-break: break-all;
}
.el-timeline-item__timestamp.is-bottom {
    margin-top: 8px;
}
.el-timeline-item__timestamp {
    color: #909399;
    line-height: 1;
    font-size: 13px;
}
.el-timeline-item__tail {
    position: absolute;
    left: 4px;
    height: 100%;
    border-left: 2px solid #e4e7ed;
}
.el-timeline-item__node--normal {
    left: -1px;
    width: 12px;
    height: 12px;
}
.el-timeline-item__node {
    position: absolute;
    background-color: #e4e7ed;
    border-radius: 50%;
    display: flex;
    justify-content: center;
    align-items: center;
}
.el-timeline .el-timeline-item:last-child .el-timeline-item__tail {
    display: none;
}
.my-form-item .el-select{
	width:100%;
}
</style>