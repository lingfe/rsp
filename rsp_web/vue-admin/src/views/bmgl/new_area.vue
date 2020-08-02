<template>
	<el-dialog  :data="showData" :title="showData.type==0?'新增省份':(showData.type==1?'新增市':'新增区域')" :visible.sync="showData.dialogVisible" width="30%">
      <el-form ref="diaform" :model="showData.form" label-width="80px" :rules='showData.rules'>
        <el-form-item label="名称" prop='name'>
          <el-input v-model="showData.form.name"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="cancel">取 消</el-button>
        <el-button type="primary" @click="sure">确 定</el-button>
      </span>
    </el-dialog>
</template>

<script>
	export default{
		name:'newbmdialog',
		props:{
			showData:{
				type:Object,
				default(){
					return{
						type:0,
						dialogVisible:false,
						notClearForm:false, 
						form:{
							name:''
						},
						rules:{
							name:[
								{ required: true, message: '编码名称不能为空', trigger: 'blur' }
							]
						}
					}
				}
			}
		},
		methods:{
			sure(){
				let $this=this;
				let back=function(){
					$this.$refs.diaform.resetFields();
				};
				this.$refs.diaform.validate((valid) => {
					this.$emit("OnSure",valid,back);
				});
			},
			cancel(){
				this.showData.dialogVisible=false;
				this.$refs.diaform.resetFields();
				if(!this.showData.notClearForm){
					this.form={
						name:''
					}
				}
			}
		}
	}
</script>

<style>
</style>