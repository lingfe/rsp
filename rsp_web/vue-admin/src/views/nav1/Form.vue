<template>
	<el-form ref="zzform" :model="form" label-width="80px" :rules='rules' @submit.prevent="onSubmit" style="margin:20px;width:60%;min-width:600px;">
		<el-form-item label="活动名称">
			<el-input v-model="form.name"></el-input>
		</el-form-item>
		<el-form-item label="活动区域">
			<el-select v-model="form.region" placeholder="请选择活动区域">
				<el-option label="区域一" value="shanghai"></el-option>
				<el-option label="区域二" value="beijing"></el-option>
			</el-select>
		</el-form-item>
		<el-form-item label="活动时间">
			<el-col :span="11">
				<el-date-picker type="date" placeholder="选择日期" v-model="form.date1" style="width: 100%;"></el-date-picker>
			</el-col>
			<el-col class="line" :span="2">-</el-col>
			<el-col :span="11">
				<el-time-picker type="fixed-time" placeholder="选择时间" v-model="form.date2" style="width: 100%;"></el-time-picker>
			</el-col>
		</el-form-item>
		<el-form-item label="即时配送">
			<el-switch on-text="" off-text="" v-model="form.delivery"></el-switch>
		</el-form-item>
		<el-form-item label="活动性质" prop='type'>
			<el-checkbox-group v-model="form.type">
				<el-checkbox label="1" name="type">美食/餐厅线上活动</el-checkbox>
				<el-checkbox label="2" name="type">地推活动</el-checkbox>
				<el-checkbox label="3" name="type">线下主题活动</el-checkbox>
				<el-checkbox label="4" name="type">单纯品牌曝光</el-checkbox>
			</el-checkbox-group>
		</el-form-item>
		<el-form-item label='美食'>
			<my-checkgroup :checkdata="checkdata"></my-checkgroup>
		</el-form-item>
		<el-form-item label='水果' prop='fruit'>
			<my-checkgroup :checkdata='fruits'></my-checkgroup>
		</el-form-item>
		<el-form-item label='是否订购'>
			<my-checkgroup :checkdata='order'></my-checkgroup>
		</el-form-item>
		<el-form-item label="特殊资源">
			<el-radio-group v-model="form.resource">
				<el-radio label="1">线上品牌商赞助</el-radio>
				<el-radio label="2">线下场地免费</el-radio>
			</el-radio-group>
		</el-form-item>
		<el-form-item label='性别' prop='sex'>
			<my-radio :radiodata='sexs'></my-radio>
		</el-form-item>
		<el-form-item label="活动形式">
			<el-input type="textarea" v-model="form.desc"></el-input>
		</el-form-item>
		<el-form-item>
			<el-button type="primary" @click='createNew'>立即创建</el-button>
			<el-button @click.native.prevent>取消</el-button>
			<el-button @click="resetForm('zzform')">重置</el-button>
		</el-form-item>
		<el-form-item  prop='zz' label='我的下拉' class='is-required'>
			<my-select :showData='selectData'>
			</my-select>
		</el-form-item>
		<el-form-item prop='zz3' label='我的多选'>
			<my-select-multiple :showData='selectDataM'>
			</my-select-multiple>
		</el-form-item>
		<el-form-item prop='zz4' label='数字'>
			<el-input type='number' v-model='num'></el-input>
		</el-form-item>
	</el-form>
</template>

<script>
	export default {
		data() {
			let $this=this;
			var checkAge = (rule, value, callback) => {
		        if (!$this.selectData.selected) {
		          return callback(new Error('必须选择'));
		        }
		      };
			return {
				num:'',
				rules:{
					type:[{type:'array',required:true,message:'必须选择',trigger:'change'}],
					zz:[{validator:checkAge,trigger:'change'}]       //封装form组件的验证，必须通过值的方式来检验，在item上添加class='is-required';
				},
				selectData:{
					selected:1,
					hasEmpty:true,
					options:[{text:'zz',value:1},{text:'asdgas',value:2,disabled:true}]
				},
				selectDataM:{
					selected:[],
					options:[{text:'zz',value:1},{text:'asdgas',value:2,disabled:true},{text:'阿斯顿噶啥的',value:3}]
				},
				form: {
					name: '',
					region: '',
					date1: '',
					date2: '',
					delivery: false,
					type: [],
					sex:'',
					resource: '',
					desc: ''
				},
				sexs:{
					checked:'',
					data:[{text:'男',id:'1'},{text:'女',id:'2'}],
					textfield:'text',
					valuefield:'id'
				},
				order:{
					data:[{id:'1'}],
					valuefield:'id',
					checkeddata:[]
				},
				checkdata:{
					name:'food',
					data:[{name:'辣椒',uid:'123',disabled:true},{name:'水果',uid:'1234'}],
					valuefield:'uid',
					textfield:'name',
					checkeddata:[]
				},
				fruits:{
//					hasAllselect:true,
//					checkAll:false,
					name:'fruit',
					data:[{text:'榴莲',id:1},{text:'香蕉',id:2},{text:'梨子',id:3}],
					textfield:'text',
					valuefield:'id',
					checkeddata:[]
				}
			}
		},
		methods: {
			onSubmit() {
				console.log('submit!');
				console.log(this.form);

			},
			createNew(){
//				console.log(this.form);
				console.log(this.num);
				console.log(this.selectData);
				console.log(this.selectDataM);
				
			},
			resetForm(formName) {
		        this.$refs[formName].resetFields();
		    }
		}
	}

</script>