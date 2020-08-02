<template>
	<section>
		<my-table :tableData="tableData" :columns="columns" @currentChange="currentChange" :loading='loading' :pageIndex='page' :pageSize='pageSize' :totalPage='totalPage'></my-table>
	</section>
</template>
<script>
	import { getUserListPage, removeUser, batchRemoveUser, editUser, addUser } from '../../api/api';
	import MyDropDown from '../comp/MyDropDown.vue'
	
export default {
    data(){
        return {
        	tableData:[],
        	loading:false,
        	page:1,
        	pageSize:20,
        	totalPage:0,
             columns: [
        { prop: "title", label: "标题" },
        { prop: "create_name", label: "发布人" },
        { prop: "item_name", label: "栏目" },
        { prop: "create_time", label: "创建时间" },
        { prop: "weight", label: "权重" },
        {
          prop: "isoriginal",
          label: "原创",
          render: function(h, param) {
            let html = "";
            if (param.row.isoriginal == "201") {
              html = "原创";
            } else {
              html = "非原创";
            }
            return html;
          }
        },
        {
        	prop:'id'
        },
        {
          prop: "",
          label: "操作",
          render: (h, param) => {
            const dropDownData = {
              label: "操作",
              items: [
                { label: "修改", func: { func: "update", id: param.row.id } },
                { label: "删除", func: { func: "del", id: param.row.id } }
              ]
            };
            // 触发MyDropDown的update和del事件
            return h(MyDropDown, {
              props: { dropDownData: dropDownData },
              on: { update: this.update, del: this.del }
            });
          }
        }
      ]
        }
    },
    methods:{
    	currentChange(a,b){
    		console.log(a,b);
    	},
    	getUsers() {
				let para = {
					page: this.page,
					pageSize:this.pageSize
				};
				this.loading= true;
				//NProgress.start();
				getUserListPage(para).then((res) => {
					this.totalPage = res.data.total;
					this.tableData = res.data.users;
					this.loading = false;
					//NProgress.done();
				});
			},
			del(a,b){
				this.$confirm('确认删除该记录吗?', '提示', {
					type: 'warning'
				}).then(() => {
					this.listLoading = true;
					//NProgress.start();
					let para = { id: row.id };
					removeUser(para).then((res) => {
						this.listLoading = false;
						//NProgress.done();
						this.$message({
							message: '删除成功',
							type: 'success'
						});
						this.getUsers();
					});
				}).catch(() => {

				});
			},
			update(index,row){
				console.log(index,row);
			}
    },
    mounted(){
    	this.getUsers();
    }
}
</script>