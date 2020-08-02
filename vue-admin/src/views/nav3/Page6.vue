<template>
	<section>
		<el-dialog  title="设备档案" :visible.sync="dialogVisible" :close-on-click-modal='false' class='equip_dia'>
			<el-tabs v-model="activeName" @tab-click="handleClick" >
			    <el-tab-pane label="设备信息" name="first">
			    	<el-form ref="form" :model="form" :inline='true' label-width='100px' :rules='rules'>
			    		<el-row class='my-form-item'>
								<el-col :span='12'>
									<el-form-item label="设备名称" prop='equipment_name'>
										<el-input v-model='form.equipment_name'></el-input>
									</el-form-item>
								</el-col>
								<el-col :span='6'>
									<el-form-item label="规格型号" prop='specification_type'>
										<el-input v-model='form.specification_type'></el-input>
									</el-form-item>
								</el-col>
								<el-col :span='6'>
									<el-form-item label="设备编号" prop='equipment_number'>
										<el-input v-model='form.equipment_number'></el-input>
									</el-form-item>
								</el-col>
			    		</el-row>
			    		<el-row class='my-form-item'>
								<el-col :span='6'>
									<el-form-item label="设备类别" prop='lb'>
										<el-input v-model='form.lb'></el-input>
									</el-form-item>
								</el-col>
								<el-col :span='6'>
									<el-form-item label="额定电压（KV）" prop='rated_voltage_kv'>
										<el-input v-model='form.rated_voltage_kv'></el-input>
									</el-form-item>
								</el-col>
								<el-col :span='6'>
									<el-form-item label="生产厂家" prop='specification_type'>
										<el-input v-model='form.specification_type'></el-input>
									</el-form-item>
								</el-col>
								<el-col :span='6'>
									<el-form-item label="使用科室" prop='equipment_number'>
										<el-input v-model='form.equipment_number'></el-input>
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
									<el-form-item label="所在场所" prop='specification_type'>
										<el-input v-model='form.specification_type'  type='textarea' :autosize='{minRows: 1}'></el-input>
									</el-form-item>
								</el-col>
			    		</el-row>
			    		<el-row class='my-form-item'>
			    			<el-col :span='6'>
			    				<el-form-item class='my-form-item-check'>
									<el-checkbox >大型诊疗许可证</el-checkbox>
								</el-form-item>
			    			</el-col>
			    			<el-col :span='12'>
			    				<el-form-item label='状态'>
									<span></span>
								</el-form-item>
			    			</el-col>
			    			<el-col :span='6'>
								<el-form-item label="上证时间" prop='specification_type'>
									<el-input v-model='form.specification_type'></el-input>
								</el-form-item>
							</el-col>
			    		</el-row>
			    		<el-row class='my-form-item'>
			    			<el-col :span='6'>
			    				<el-form-item class='my-form-item-check'>
									<el-checkbox >辐射安全许可证</el-checkbox>
								</el-form-item>
			    			</el-col>
			    			<el-col :span='12'>
			    				<el-form-item label='状态'>
									<span></span>
								</el-form-item>
			    			</el-col>
			    			<el-col :span='6'>
								<el-form-item label="上证时间" prop='specification_type'>
									<el-input v-model='form.specification_type'></el-input>
								</el-form-item>
							</el-col>
			    		</el-row>
			    		<el-row class='my-form-item'>
			    			<el-col :span='6'>
			    				<el-form-item class='my-form-item-check'>
									<el-checkbox >大型配置许可证</el-checkbox>
								</el-form-item>
			    			</el-col>
			    			<el-col :span='12'>
			    				<el-form-item label='状态'>
									<span></span>
								</el-form-item>
			    			</el-col>
			    			<el-col :span='6'>
								<el-form-item label="上证时间" prop='specification_type'>
									<el-input v-model='form.specification_type'></el-input>
								</el-form-item>
							</el-col>
			    		</el-row>
			    		<el-row class='my-form-item'>
								<el-col :span='12'>
									<el-form-item label="来源" prop='lb'>
										<el-input v-model='form.lb'></el-input>
									</el-form-item>
								</el-col>
								<el-col :span='12'>
									<el-form-item label="去向" prop='specification_type'>
										<el-input v-model='form.specification_type'></el-input>
									</el-form-item>
								</el-col>
			    		</el-row>
			    		<el-row class='my-form-item'>
			    			<el-col :span='6'>
			    				<el-form-item class='my-form-item-check'>
									<el-checkbox >报废</el-checkbox>
								</el-form-item>
			    			</el-col>
			    			<el-col :span='6'>
								<el-form-item label="报废时间" prop='specification_type'>
									<el-input v-model='form.specification_type'></el-input>
								</el-form-item>
							</el-col>
			    		</el-row>
			    		<el-row class='my-form-item'>
			    			<el-col :span='6'>
			    				<el-form-item class='my-form-item-check'>
									<el-checkbox >设备年检</el-checkbox>
								</el-form-item>
			    			</el-col>
			    			<el-col :span='6'>
								<el-form-item label="设备年检时间" prop='specification_type'>
									<el-input v-model='form.specification_type'></el-input>
								</el-form-item>
							</el-col>
							<el-col :span='6'>
								<el-form-item label="周期" prop='specification_type'>
									<el-input v-model='form.specification_type'></el-input>
								</el-form-item>
							</el-col>
							<el-col :span='6'>
								<el-form-item label="下次时间" prop='specification_type'>
									<el-input v-model='form.specification_type'></el-input>
								</el-form-item>
							</el-col>
			    		</el-row>
      				</el-form>
			    </el-tab-pane>
			    <el-tab-pane label="资料信息" name="second">
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
			    <el-tab-pane label="痕迹信息" name="third">
			    	<my-timeline :showData='timeline'></my-timeline>
			    </el-tab-pane>
			</el-tabs>
      
      <span slot="footer" class="dialog-footer">
        <el-button @click="cancel">取 消</el-button>
        <el-button type="primary" @click="sure" :loading='btnLoading'>确 定</el-button>
      </span>
    </el-dialog>
	</section>
</template>

<script>
	export default{
		name:'equipDia',
		data(){
			return {
				activeName:'first',
				dialogVisible:true,
				btnLoading:false,
				form:{
					equipment_name:'',
					specification_type:'',
					equipment_number:'',
					lb:'',
					purpose:'asdfasdf',
					rated_voltage_kv:'5'
				},
				rules:{
					
				},
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
			sure(){},
			handleClick(tab, event) {
		        console.log(tab, event);
		   },
		    handleNodeClick(data) {
		        console.log(data);
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
</style>