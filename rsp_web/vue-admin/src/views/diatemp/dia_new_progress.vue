<template>
    <el-dialog :data='showData' :title='showData.title':visible.sync='showData.dialogVisible' class='my-form-dialog' :before-close='cancel'>
    	<el-form :inline='true' label-width='100px' :rules='showData.rules' >
    		<el-row class='my-form-item'>
					<el-col :span='24'>
						<el-form-item label="流程名称" prop='name'>
							<el-input v-model='showData.form.name'></el-input>
						</el-form-item>
					</el-col>
    		</el-row>
    		<el-row class='my-form-item'>
    			<el-col :span='8'>
    				<el-form-item label="省份" class='is-required'>
						<my-select :showData='showData.provinces'></my-select>
					</el-form-item>
    			</el-col>
    			<el-col :span='8'>
    				<el-form-item label="流程类型" class='is-required'>
						<my-select :showData='showData.types'></my-select>
					</el-form-item>
    			</el-col>
    			<el-col :span='8'>
    				<el-form-item label="流程分类" class='is-required'>
						<my-select :showData='showData.cates'></my-select>
					</el-form-item>
    			</el-col>
    		</el-row>
    		<el-row class='my-form-item'>
					<el-col :span='24'>
						<el-form-item label="备注" prop='bz'>
							<el-input type='textarea' resize='none' v-model='showData.form.bz'></el-input>
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
							<el-date-picker type="date" placeholder="选择日期" v-model="showData.date1"></el-date-picker>
						</el-form-item>
    			</el-col>
    			<el-col :span='8'>
    				<el-form-item  label='附件' class='my-form-item-check my-form-item-check_input'>
							<el-checkbox >附件</el-checkbox>
							<my-select :showData='showData.fj1'></my-select>
						</el-form-item>
    			</el-col>
    		</el-row>
    		<el-row class='my-form-item'>
    			<el-col :span='8'>
    				<el-form-item label='关联证照'  class='my-form-item-check my-form-item-check_input'>
							<el-checkbox >关联证照</el-checkbox>
							<my-select :showData='showData.licensetypes'></my-select>
						</el-form-item>
    			</el-col>
    			<el-col :span='8'>
    				<el-form-item label='时间'  class='my-form-item-check my-form-item-check_input'>
							<el-checkbox >时间</el-checkbox>
							<el-date-picker type="date" placeholder="选择日期" v-model="showData.date2"></el-date-picker>
						</el-form-item>
    			</el-col>
    			<el-col :span='8'>
    				<el-form-item label='附件' class='my-form-item-check my-form-item-check_input'>
							<el-checkbox >附件</el-checkbox>
							<my-select :showData='showData.fj2'></my-select>
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

	export default{
		props:{
			showData:{
				type:Object,
				default(){
					return {
						title:'新增流程',
						dialogVisible:false,
						date1:'',
						date2:'',
						cancelFlag:false,
						provinces:{}, //详见myselect
						types:{},
						cates:{},
						fj1:{},
						licensetypes:{},
						fj2:{},
						rules:{}
					}
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
				if(!this.showData.cancelFlag)return this.closeWin(); 
				this.$emit('onCancel',this.closeWin);
			}
		},
		mounted(){
			console.log(this);
//			this.showData.rules={
//				name:[{ required: true, message: '请输入名称', trigger: 'blur' }],
//				bz:[{required:true,message:'备注不能为空',trigger:'blur'}]
//			};
		}
	}
</script>

<style>
</style>