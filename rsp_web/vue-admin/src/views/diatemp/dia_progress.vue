<template>
    <el-dialog :data='showData' :title='showData.title' :visible.sync='showData.dialogVisible' class='my-form-dialog' :before-close='cancel'>
    	<el-form :inline='true' ref="form" :model='form'  label-width='100px' :rules='rules' >
    		<el-row class='my-form-item'>
					<el-col :span='24'>
						<el-form-item label="流程名称" prop='name'>
							<el-input v-model='form.name'></el-input>
						</el-form-item>
					</el-col>
    		</el-row>
    		<el-row class='my-form-item'>
    			<el-col :span='8'>
    				<el-form-item label="省份" class='is-required'>
						<my-select :showData='provinces'></my-select>
					</el-form-item>
    			</el-col>
    			<el-col :span='8'>
    				<el-form-item label="流程类型" class='is-required'>
						<my-select :showData='types'></my-select>
					</el-form-item>
    			</el-col>
    			<el-col :span='8'>
    				<el-form-item label="流程分类" class='is-required'>
						<my-select :showData='cates'></my-select>
					</el-form-item>
    			</el-col>
    		</el-row>
    		<el-row class='my-form-item'>
					<el-col :span='24'>
						<el-form-item label="备注" prop='bz'>
							<el-input type='textarea' resize='none' v-model='form.bz'></el-input>
						</el-form-item>
					</el-col>
    		</el-row>
    		<el-row class='my-form-item'>
    			<el-col :span='8'>
    				<el-form-item class='my-form-item-check'>
							<el-checkbox >关联设备</el-checkbox>
						</el-form-item>
    			</el-col>
    			<el-col :span='8'>
    				<el-form-item label='时间'  class='my-form-item-check my-form-item-check_input'>
							<el-checkbox >时间</el-checkbox>
							<el-date-picker type="date" placeholder="选择日期" v-model="date1"></el-date-picker>
						</el-form-item>
    			</el-col>
    			<el-col :span='8'>
    				<el-form-item  label='附件' class='my-form-item-check my-form-item-check_input'>
							<el-checkbox >附件</el-checkbox>
							<my-select :showData='fj1'></my-select>
						</el-form-item>
    			</el-col>
    		</el-row>
    		<el-row class='my-form-item'>
    			<el-col :span='8'>
    				<el-form-item label='关联证照'  class='my-form-item-check my-form-item-check_input'>
							<el-checkbox >关联证照</el-checkbox>
							<my-select :showData='licensetypes'></my-select>
						</el-form-item>
    			</el-col>
    			<el-col :span='8'>
    				<el-form-item label='时间'  class='my-form-item-check my-form-item-check_input'>
							<el-checkbox >时间</el-checkbox>
							<el-date-picker type="date" placeholder="选择日期" v-model="date2"></el-date-picker>
						</el-form-item>
    			</el-col>
    			<el-col :span='8'>
    				<el-form-item label='附件' class='my-form-item-check my-form-item-check_input'>
							<el-checkbox >附件</el-checkbox>
							<my-select :showData='fj2'></my-select>
						</el-form-item>
    			</el-col>
    		</el-row>
    	</el-form>
    	 <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="upload">导 入</el-button>
        <el-button type="primary" @click="sure">保 存</el-button>
        <el-button @click="cancel">取 消</el-button>
      </span>
    </el-dialog>
</template>

<script>
	import Config from '../../config.js'
	
	export default{
		props:{
			showData:{
				type:Object,
				default(){
					return {
						title:'新增流程',   
						dialogVisible:false,  
						type:0  //0默认是新增流程 1流程详情，用于后续操作。所有的dialog操作都在本页进行，不用传递过多参数。
					}
				}
			}
		},
		data(){
			return {
				date1:'',
				date2:'',
				form:{
					name:'',
					bz:''
				},
				provinces:{
  				    selected:'',
					options:[{text:'北京',value:1},{text:'上海',value:2}]
      			},
      			types:{
  				  	selected:'',
					options:Config.progresstypes
      			},
      			cates:{
  				  	selected:'',
					options:Config.progresscates
      			},
	      		fj1:{
	      			selected:'',
					options:Config.fj1
	      		},
	      		fj2:{
	      			selected:'',
					options:Config.fj2
	      		},
	      		licensetypes:{
	      			selected:'',
					options:Config.licensetypes
	      		},
	      		rules:{
	      			name:[{ required: true, message: '流程名称不能为空', trigger: 'blur'}]
	      		}
			}
		},
		methods:{
			closeWin(){
				this.showData.dialogVisible=false;
			},
			upload(){
				this.$message.error('稍后呈现');
			},
			sure(){
				this.$emit('onSure',this.closeWin);
			},
			cancel(){
				this.$refs['form'].resetFields();
				if(!this.showData.cancelFlag)return this.closeWin(); 
				this.$emit('onCancel',this.closeWin);
			}
		},
		mounted(){
			this.form.name=this.showData.name||'';
			console.log(this);
		}
	}
</script>

<style>
</style>