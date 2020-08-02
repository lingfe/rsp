<template>
	<el-dialog  :data="showData" :title="calcTitle()" :visible.sync="showData.dialogVisible" :close-on-click-modal='false'>
      <el-form ref="form" :model="form" label-width="80px" :rules='rules'>
        <el-form-item :label="calcLabel()" prop='coding_name'>
          <el-input v-model="form.coding_name"></el-input>
        </el-form-item>
        <el-form-item label='是否预排' v-if='showData.coding_type==10006||showData.coding_type==20003||showData.coding_type==40001' prop='flag_yp'>
			<el-checkbox v-model="form.flag_yp"></el-checkbox>
		</el-form-item>
		 <el-form-item label="预排名称" v-if='showData.coding_type==10006||showData.coding_type==20003||showData.coding_type==40001' prop='yp_name'>
          <el-input v-model="form.yp_name"></el-input>
        </el-form-item>
         <el-form-item label="预排周期" v-if='showData.coding_type==10006||showData.coding_type==20003||showData.coding_type==40001' prop='yp_cycle'>
          <el-input v-model="form.yp_cycle" type='number' min='0'></el-input>
        </el-form-item>
        <el-form-item label="备注" prop='remark'>
          <el-input v-model="form.remark" type="textarea"></el-input>
        </el-form-item>
        <el-form-item label='是否停用' v-if='showData.type!=0' prop='stop_flag'>
			<el-checkbox v-model="form.stop_flag"></el-checkbox>
		</el-form-item>
		<el-form-item label="停用说明" prop='stop_explain' v-if='showData.type!=0'>
          <el-input v-model="form.stop_explain" type="textarea" ></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="cancel">取 消</el-button>
        <el-button type="primary" @click="sure" :loading='btnLoading'>确 定</el-button>
      </span>
    </el-dialog>
</template>

<script>
	import { createNewBm,editBm,createNewBmDe,editBmDe} from '../../api/api'
	
	export default{
		name:'newbmdialog',
		data(){
			let $this = this;
			 var check_yp_name = (rule, value, callback) => {
		        setTimeout(() => {
		        	if($this.form.flag_yp&&!value){
		        		callback(new Error('请填写预排名称'));
		        	}else callback();
		        }, 500);
		      };
		       var check_yp_cycle = (rule, value, callback) => {
		        setTimeout(() => {
		        	if($this.form.flag_yp){
		        		if(!value)return callback(new Error('请填写预排周期'));
		        		if(value<1)return callback(new Error('预排周期必须大于0'));
		        		callback();
		        	}else callback();
		        }, 500);
		      };
			return {
				form:{
					coding_name:'',
					flag_yp:0,
					yp_name:'',
					yp_cycle:'',
					remark:'',
					stop_flag:0,
					stop_explain:''
				},
				rules:{
					coding_name:[{required:true,message:'编码名称不能为空',trigger:'blur'}],
					yp_name:[{ validator: check_yp_name, trigger: 'blur' }],
					yp_cycle:[{ validator: check_yp_cycle, trigger: 'blur' }],
				},
				btnLoading:false
			}
		},
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
		methods:{
			calcTitle(){
				let $this=this;
				if($this.showData.title)return $this.showData.title;
				else return $this.showData.type==0?'新增编码':'编辑编码'
			},
			calcLabel(){
				let $this=this;
				if($this.showData.label)return $this.showData.label;
				else return '编码名称';
			},
			sure(){
				var $this=this;
				this.$refs.form.validate((valid) => {
				if(!valid)return false;
				if($this.showData.type==0){
					$this.btnLoading=true;
					let para=Object.assign({coding_type:$this.showData.coding_type},$this.form,{flag_yp:$this.form.flag_yp?1:0});
					if($this.showData.dFlag)
					return createNewBmDe(para).then(res => {
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
					}).catch(() => {
						$this.btnLoading=false;
					});
					createNewBm(para).then(res => {
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
					}).catch(() => {
						$this.btnLoading=false;
					});
					return false;
				}
				$this.$confirm('确定这样修改').then(() => {
					$this.btnLoading=true;
					let para=Object.assign({id:$this.showData.form.id,coding_type:$this.showData.coding_type},
						$this.form,
						{stop_flag:$this.form.stop_flag?0:1,flag_yp:$this.form.flag_yp?1:0});
					if($this.showData.dFlag)
					return editBmDe(para).then(res => {
						let {state,msg,data}=res;
						$this.btnLoading=false;
						$this.diaLoading=false;
						if(state!=200)return $this.$message.error(msg);
						$this.showData.dialogVisible=false;
						$this.$message({
							message:'修改成功',
							type:'success'
						});
						this.$emit('OnSure',true);
					});
					editBm(para).then(res => {
						let {state,msg,data}=res;
						$this.btnLoading=false;
						$this.diaLoading=false;
						if(state!=200)return $this.$message.error(msg);
						$this.showData.dialogVisible=false;
						$this.$message({
							message:'修改成功',
							type:'success'
						});
						this.$emit('OnSure',true);
					}).catch(e => {
						$this.btnLoading=false;
					});
					return false;
				});

				});
			},
			cancel(){
				this.showData.dialogVisible=false;
			}
		},
		mounted(){
			
		},
		beforeUpdate(){
			if(!this.showData.freshFlag){
				if(this.$refs.form)this.$refs.form.resetFields();
				this.form=Object.assign({
					coding_name:'',
					flag_yp:0,
					yp_name:'',
					yp_cycle:'',
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
</style>