<template>
<el-row>
    <el-row>
        <el-table
            :data="tableData"
            border
            stripe
            style="width: 100%;text-align:center"
            :header-cell-style="headerStyle" v-loading='loading'>
            <el-table-column
                v-for="(column, index) in columns"
                :prop="column.prop"
                :key="index"
                :label="column.label">
                <template slot-scope="scope">
                    <my-render v-if="column.render" :row="scope.row" :render="column.render"></my-render>
                    <span v-else>
                    {{scope.row[column.prop]}}
                    </span>
                </template>
            </el-table-column>
        </el-table>
    </el-row>
    <el-row>
        <div class="block">
            <el-pagination
            :current-page="pageIndex"
            :page-size="pageSize"
            @current-change="currentChange"
            layout="total, sizes, prev, pager, next, jumper"
            :total="totalPage">
            </el-pagination>
        </div>
    </el-row>
</el-row>
</template>

<script>
import MyRender from "./MyRender";
export default {
  props: {
    tableData: Array,
    columns: Array,
    loading:{type:Boolean,default:false},
    pageIndex: { type: Number, default: 1 },
    pageSize: { type: Number, default: 10 },
    totalPage:{ type: Number, default:0}
  },
  methods: {
    headerStyle() {
      return { "text-align": "center" };
    },
    currentChange(val){
        this.$emit('currentChange',val)
    }
  },
  components: {
    MyRender
  }
};
</script>

<style>

</style>