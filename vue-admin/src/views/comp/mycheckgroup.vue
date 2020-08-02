<template>
	<div>
		<template :data="checkdata">
			<el-checkbox v-if='checkdata.hasAllselect' v-model="checkdata.checkAll" @change="handleCheckAllChange" >全选</el-checkbox>
			<el-checkbox-group   v-model="checkdata.checkeddata" @change="handleCheckedChange">
			    <el-checkbox  
					v-for="(data,index1) in checkdata.data" 
					:label="data[checkdata.valuefield]"
					:name="checkdata.name"   
					:key="data[checkdata.valuefield]" 
					:disabled="data.disabled?data.disabled:false"
					v-model='data.checked'
					>
					{{data[checkdata.textfield]}}
				</el-checkbox>
			 </el-checkbox-group>
		</template>
	</div>
</template>

<script>
	export default{
		props:{
			checkdata:{
				type:Object,
				default(){
					return {
						name:'name',                 //name属性用于表单获取值
						checkAll:false,             //全选状态
						hasAllselect:false,			//是否有全选选框
						data:[{text:'默认',id:1,checked:false}],   //选项的数据
						checkeddata:[],				//选中的数据
						valuefield:'id',			//选项的值域
						textfield:'text',			//选项的名字
					}
				}
			}
		},
		methods:{
			handleCheckAllChange(val){
				let flag=typeof val=='boolean'?val:val.target.checked;
				let data=this.checkdata.data;
				let vf=this.checkdata.valuefield;
				this.checkdata.checkeddata=flag?data.map(function(d){return d[vf]}):[];
				this.$emit('onHandleCheckAllChange',val);
			},
			handleCheckedChange(val){
				let checkedCount = val.length;
        		this.checkdata.checkAll = checkedCount === this.checkdata.data.length;
        		this.$emit('onHnadleCHeckedChange',val);
			}
		}
	}
</script>

<style>
</style>