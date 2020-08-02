<template :data='radiodata'>
	<el-radio-group v-model="radiodata.checked" @change='handleChanged' :size='radiodata.size'>
		<template v-if='!radiodata.type'>
			<el-radio v-for='data in radiodata.data' :label="data[radiodata.valuefield]" :key='data[radiodata.valuefield]'>{{data[radiodata.textfield]}}</el-radio>
		</template>
		<template v-else>
			<el-radio-button v-for='data in radiodata.data' :label="data[radiodata.valuefield]" :key='data[radiodata.valuefield]'>{{data[radiodata.textfield]}}</el-radio-button>
		</template>
	</el-radio-group>
</template>

<script>
	export default{
		props:{
			radiodata:{
				type:Object,
				default(){
					return {
						checked:'',
						type:0,
						size:'medium',
						data:[{text:'默认',id:'1'}],
						textfield:'text',
						valuefield:'id'
					}
				}
			}
		},
		methods:{
			handleChanged(value){
	  			this.$emit('onRadioChange',value);
			}
		}
	}
</script>

<style>
</style>