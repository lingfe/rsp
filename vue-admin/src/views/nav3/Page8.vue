<template>
	<section>
		<el-dialog  title="新增科室" :visible.sync="dialogVisible" :close-on-click-modal='false' class='depart_dia'>
			    	<el-form ref="form" :model="form" :inline='true' label-width='100px' :rules='rules'>
			    		<el-row class='my-form-item'>
								<el-col :span='12'>
									<el-form-item label="科室名称" prop='department_name'>
										<el-input v-model='form.department_name'></el-input>
									</el-form-item>
								</el-col>
								<el-col :span='12'>
									<el-form-item label="科室属性" prop='department_coding_id'>
										<el-select v-model="form.department_coding_id">
									      <el-option v-for='(item,i) in department_attributeArr' :label="item.coding_name" :key='item.id' :value="item.id"></el-option>
									    </el-select>
									</el-form-item>
								</el-col>
			    		</el-row>
			    		<el-row class='my-form-item'>
								<el-col :span='12'>
									<el-form-item label="电话" prop='phone'>
										<el-input v-model='form.phone'></el-input>
									</el-form-item>
								</el-col>
								<el-col :span='12'>
									<el-form-item label="负责人" prop='responsible_perso'>
										<el-input v-model='form.responsible_perso'></el-input>
									</el-form-item>
								</el-col>
			    		</el-row>
			    		<el-row class='my-form-item'>
								<el-col :span='24'>
									<el-form-item label="备注" prop='remark'>
										<el-input v-model='form.remark' type='textarea' :autosize='{minRows: 2}'></el-input>
									</el-form-item>
								</el-col>
			    		</el-row>
			    		<el-row class='my-form-item'>
			    			<el-col :span='6'>
			    				 <el-form-item label='是否停用' prop='stop_flag'>
									<el-checkbox v-model="form.stop_flag"></el-checkbox>
								</el-form-item>
			    			</el-col>
			    		</el-row>
			    		<el-row class='my-form-item'>
								<el-col :span='24'>
									<el-form-item label="停用说明" prop='purpose'>
										<el-input v-model='form.purpose' type='textarea' :autosize='{minRows: 2}'></el-input>
									</el-form-item>
								</el-col>
			    		</el-row>
      		</el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="cancel">取 消</el-button>
        <el-button type="primary" @click="sure" :loading='btnLoading'>确 定</el-button>
      </span>
    </el-dialog>
	</section>
</template>

<script>
	import { getBmPersonDe} from '../../api/api'
	
	export default{
		name:'equipDia',
		data(){
			return {
				btnLoading:false,
				dialogVisible:true,
				rules:{
					department_name:[{required:true,message:'科室名称不能为空',trigger:'blur'}],
					department_coding_id:[{required:true,message:'请选择科室属性',trigger:'blur'}]
				},
				form:{
					department_name:'',
					department_coding_id:'',
					phone:'',
					responsible_perso:'',
					remark:'',
					stop_flag:0,
					stop_explain:''
				},
				department_attributeArr:[]
			}
		},
		methods:{
			cancel(){},
			sure(){
				let $this=this;
				$this.$refs.form.validate((valid) => {
					if(valid)console.log(1);
				});
			}
		},
		mounted(){
			let para={
				stop_flag:1,
				pageIndex:1,
				pageNum:999
			}
			getBmPersonDe(para).then((res) => {
				let {state,msg,data}=res;
				if(state!=200){
					$this.$message({
					message:msg||'科室属性查询失败，请关闭窗口重试',
					type:'error'
				});
				this.dataTable.data=[];
				}else
				this.department_attributeArr=res.data.list;
			});
		}
	}
</script>

<style>
	.depart_dia .el-dialog {
    width: 675px;
}
</style>