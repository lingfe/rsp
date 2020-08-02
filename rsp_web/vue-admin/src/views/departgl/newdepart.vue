<template>
		<el-dialog :data='showData'  :title="calcTitle()" :visible.sync="showData.dialogVisible" :close-on-click-modal='false' class='depart_dia'>
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
			    		<el-row class='my-form-item' v-if='showData.type!=0'>
			    			<el-col :span='6'>
			    				 <el-form-item label='是否停用' prop='stop_flag'>
									<el-checkbox v-model="form.stop_flag"></el-checkbox>
								</el-form-item>
			    			</el-col>
			    		</el-row>
			    		<el-row class='my-form-item' v-if='showData.type!=0'>
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
</template>

<script>
	import { getBmPersonDe,createNewBmDeG,editBmDeG} from '../../api/api'
	
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
				this.$refs.form.validate((valid) => {
				if(!valid)return false;
				let department_attribute=$this.department_attributeArr.filter(d => d.id==$this.form.department_coding_id)[0].coding_name;
				if($this.showData.type==0){
					$this.btnLoading=true;
					let para=Object.assign({pid:$this.showData.pid,department_attribute:department_attribute},$this.form);
					createNewBmDeG(para).then(res => {
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
					let para=Object.assign({pid:$this.showData.pid},$this.form,{
						stop_flag:$this.form.stop_flag?0:1,
						department_attribute:department_attribute
					});
					editBmDeG(para).then(res => {
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
			calcTitle(){
				let $this=this;
				return $this.showData.type==0?'新增科室':'科室详情编辑';
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
		},
		beforeUpdate(){
			if(!this.showData.freshFlag){
				if(this.$refs.form)this.$refs.form.resetFields();
				this.form=Object.assign({
					department_name:'',
					department_coding_id:'',
					phone:'',
					responsible_perso:'',
					remark:'',
					stop_flag:0,
					stop_explain:''
				},this.showData.form||{});
				this.showData.freshFlag=1;
			}
		}
	}
</script>

<style>
	.depart_dia .el-dialog {
    width: 675px;
}
</style>