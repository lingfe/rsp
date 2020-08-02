<template :data='showData'>
	<el-select 
		class='my_multiple'
		v-model="showData.selected"
		@change='handlechange'
		multiple
		:disabled='showData.disabled'
		 :placeholder="showData.placeholder||''">
    <el-option
      v-for="item in showData.options"
      :key="item[showData.valuefield||'value']"
      :label="item[showData.textfield||'text']"
      :disabled='item.disabled'
      :value="item[showData.valuefield||'value']">
    </el-option>
  </el-select>
</template>

<script>
	export default{
		props:{
			showData:{
				type:Object,
				default(){
					return {
						selected:[],           //选中的值，可以设置默认值 选中的值必须是全等  1===1
						disabled:false,        //下拉是否可用
						options:[],            //选项的值，示例{text:1234,value:1,disabled:true}.   显示文本、值、是否不可选
						valuefield:'value',    //值域
						textfield:'text',	   //文本域
						keyfield:'value'       //键域
					}
				}
			}
		},
		methods:{
			handlechange(val){
				this.$emit('onChange',val);
			}
		}
	}
</script>

<style>
	.my_multiple .el-tag .el-icon-close{
		line-height: 20px;
	}
</style>