<template>
  <div>
    <el-button type="text" @click="newadd">编辑</el-button>
    <el-button type="text" @click="newadd2">编辑</el-button>
    <el-button type="text" @click="newadd3">新增流程</el-button>
    <el-button type="text" @click="newadd4">新增流程3</el-button>
    <el-dialog title="表单弹框" width='900px' :visible.sync='dialogVisible'>
      <el-form ref="forms" :model="form" label-width="90px" :rules='rules'>
        <el-form-item label="姓名" prop='name'>
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="号码" prop='number'>
          <el-input v-model="form.number"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
      </span>
    </el-dialog>
    <dia-cus :data='form2'></dia-cus>
    <el-dialog
    		title='拖拽弹窗'
        v-dialogDrag
        ref="dialog__wrapper">
        <div class="dialog-body">
            <div 
                class="line"
                v-dialogDragWidth="$refs.dialog__wrapper">
             <span slot="footer" class="dialog-footer">
			        <el-button @click="dialogVisible = false">取 消</el-button>
			        <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
			      </span>
            </div>
        </div>
    </el-dialog>
		<my-new-progress 
			:showData='proData'
			@onSure='onSure'
			></my-new-progress>
			<my-progress :showData='newPro'></my-progress>
  </div>
</template>

<script>
	import Config from '../../config.js'
	
  export default {
    data() {
    	var $this=this;
    	var checkthis=(rule,value,callback) => {
    		console.log(value,$this.proData.form.name)
    		return callback();
    	}
      return {
      	newPro:{
      		title:'新增流程',
      		dialogVisible:false,
      		name:'233'
      	},
      	rules:{
      			name:[{required:true,message:'不能空',trigger:'blur'}],
      			phone:[{required:true,message:'不能空',trigger:'blur'}]
      		},
      		dialogVisible:false,
      	proData:{
      		title:'新增流程',
      		dialogVisible:false,
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
      			name:[ { validator: checkthis, trigger: 'blur' }]
      		}
      	},
        dialogVisible:false,
        form: {
          name: '',
          number:''
        },
        form2:{
        	 dialogVisible:false,
        form: {
          name: '',
          number:''
        },
        callback:function(data,back){
        	console.log(this);
        	console.log(data,back);
        	back();
        }
        }
      };
    },
    methods:{
    	handleclose(){
    		console.log(1);
    	},
    	onSure(back){
    		console.log(this.proData);
    	},
    	newadd:function(){
    		var $this=this;
    		console.log($this.$refs)
    		$this.dialogVisible=true;
//  		$this.form.name='';
//  		$this.form.number='';
    	},
    	newadd2:function(){
    		var $this=this;
    		$this.form2.dialogVisible=true;
    		$this.form2.form.name='111';
    		$this.form2.form.number=1654;
    	},
    	newadd3(){
    		this.proData.dialogVisible=true;
    	},
    	newadd4(){
    		this.newPro.dialogVisible=true;
    	}
    }
  };
</script>
<style>
	.my-form-dialog .el-dialog{
		width:900px;
	}
	.my-form-item .el-form-item{
		width:100%;
	}
	.my-form-item .el-form-item .el-form-item__content{
		width:calc(100% - 100px);
	}
	.my-form-item-check>label{
		opacity: 0;
	}
	.my-form-item-check .el-checkbox{
		 width: 88px;
    padding-right: 12px;
		text-align: right;
	}
.my-form-item-check.my-form-item-check_input .el-checkbox {
    position: absolute;
    left: -100px;
    top: 0;
   
}
.my-form-item .el-date-editor.el-input{
		width:auto;
}
</style>