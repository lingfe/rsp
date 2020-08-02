<template>
	<el-dialog title="表单弹框" :visible.sync="showData.dialogVisible" width="30%" :data="showData">
      <el-form ref="form" :model="showData.form" label-width="80px" :rules='rules'>
        <el-form-item label="姓名" prop='name'>
          <el-input v-model="showData.form.name"></el-input>
        </el-form-item>
        <el-form-item label="号码">
          <el-input v-model="showData.form.number"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="showData.dialogVisible = false" z='3'>取 消</el-button>
        <el-button type="primary" @click="sure">确 定</el-button>
      </span>
    </el-dialog>
</template>

<script>
	export default {
	  props: {
	    data: {
	      default () {
	        return {
	        }
	      },
	      type: Object
	    }
	  },
	  data () {
	    return {
	      showData:{
	      	 dialogVisible:false,
		      form: {
		      	name:'',
		      	number:''
		      },
		     callback:function(data,back){
				back();
		     }
	      },
	      rules:{
	      	name:[{min:3,max:8,message:'asdg',trigger:'blur'}]
	      }
	    }
	  },
	  methods: {
	  	sure:function(){
	  		var $this=this;
	  		$this.$refs.form.validate((valid) => {
	  			console.log(valid);
	  			if(valid){
	  				$this.$confirm('queding?','tips',{}).then(() => {
	  					if(!$this.showData.callback){
	  						console.log('请写回调');
	  						return false;
	  					}
	  					$this.showData.callback($this.showData.form,function(){
	  					$this.showData.dialogVisible = false;	  						
	  					});
	  				});
	  			}
				});
	  	}
	  },
	  mounted () {
	    this.showData = this.data;
	  }
	}	
</script>

<style>
</style>