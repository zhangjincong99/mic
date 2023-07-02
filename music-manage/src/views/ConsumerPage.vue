<template>
  <div class="container">
    <div class="handle-box">
      <el-button @click="deleteAll">批量删除</el-button>
      <el-input v-model="searchWord" placeholder="筛选用户"></el-input>
      <el-button type="primary" @click="centerDialogVisible = true">添加新用户</el-button>
    </div>
    <el-table height="665px" border size="small" :data="data" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="40" align="center"></el-table-column>
      <el-table-column label="ID" prop="id" width="50" align="center"></el-table-column>
      <el-table-column label="用户头像" width="102" align="center">
        <template v-slot="scope">
          <img :src="attachImageUrl(scope.row.avator)" style="width: 80px" />
          <el-upload :action="uploadUrl(scope.row.id)" :show-file-list="false" :on-success="handleImgSuccess"
            :before-upload="beforeImgUpload">
            <el-button>更新图片</el-button>
          </el-upload>
        </template>
      </el-table-column>
      <el-table-column label="用户名" prop="username" width="80" align="center"></el-table-column>
      <el-table-column label="性别" width="50" align="center">
        <template v-slot="scope">
          <div>{{ changeSex(scope.row.sex) }}</div>
        </template>
      </el-table-column>
      <el-table-column label="手机号码" prop="phoneNum" width="120" align="center"></el-table-column>
      <el-table-column label="邮箱" prop="email" width="130" align="center"></el-table-column>
      <el-table-column label="生日" width="100" align="center">
        <template v-slot="scope">
          <div>{{ getBirth(scope.row.birth) }}</div>
        </template>
      </el-table-column>
      <el-table-column label="签名" prop="introduction"></el-table-column>
      <el-table-column label="地区" prop="location" width="70" align="center"></el-table-column>
      <el-table-column label="收藏" width="90" align="center">
        <template v-slot="scope">
          <el-button @click="goCollectPage(scope.row.id)">收藏</el-button>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="160" align="center">
        <template v-slot="scope">
          <el-button @click="editRow(scope.row)">编辑</el-button>
          <el-button type="danger" @click="deleteRow(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination class="pagination" background layout="total, prev, pager, next" :current-page="currentPage"
      :page-size="pageSize" :total="tableData.length" @current-change="handleCurrentChange">
    </el-pagination>
  </div>

  <!-- 添加 -->
  <el-dialog title="添加新用户" v-model="centerDialogVisible">
    <el-form label-width="80px" :model="registerForm" :rules="userRule">
      <el-form-item label="用户名" prop="username">
        <el-input v-model="registerForm.username" placeholder="用户名"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input type="password" v-model="registerForm.password" placeholder="密码"></el-input>
      </el-form-item>
      <el-form-item label="性别" prop="sex">
        <el-radio-group v-model="registerForm.sex">
          <!-- <input type="radio" name="sex" value="0" v-model="registerForm.sex">&nbsp;女&nbsp;&nbsp;
          <input type="radio" name="sex" value="1" v-model="registerForm.sex">&nbsp;男 -->
          <el-radio :label="0">女</el-radio>
          <el-radio :label="1">男</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="手机号" prop="phoneNum">
        <el-input v-model="registerForm.phoneNum" placeholder="手机号"></el-input>
      </el-form-item>
      <el-form-item label="电子邮箱" prop="email">
        <el-input v-model="registerForm.email" placeholder="电子邮箱"></el-input>
      </el-form-item>
      <el-form-item label="签名" prop="introduction">
        <el-input v-model="registerForm.introduction" placeholder="签名"></el-input>
      </el-form-item>
      <el-form-item label="出生日期" prop="birth">
        <el-date-picker type="date" v-model="registerForm.birth"></el-date-picker>
      </el-form-item>
      <el-form-item label="地区" prop="location">
        <el-input v-model="registerForm.location" placeholder="地区"></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="centerDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addConsumer">确 定</el-button>
      </span>
    </template>
  </el-dialog>

  <!-- 编辑弹出框 -->
  <el-dialog title="修改用户" v-model="editVisible">
    <el-form label-width="60px" :model="editForm" :rules="userRule">
      <el-form-item label="用户名" prop="username">
        <el-input v-model="editForm.username"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input type="password" v-model="editForm.password"></el-input>
      </el-form-item>
      <el-form-item label="性别" prop="sex">
        <el-radio-group v-model="editForm.sex">
          <el-radio :label="0">女</el-radio>
          <el-radio :label="1">男</el-radio>
          <!-- <input type="radio" name="sex" value="0" v-model="registerForm.sex">&nbsp;女&nbsp;&nbsp; -->
          <!-- <input type="radio" name="sex" value="1" v-model="registerForm.sex">&nbsp;男 -->
        </el-radio-group>
      </el-form-item>
      <el-form-item label="手机号" prop="phoneNum">
        <el-input v-model="editForm.phoneNum"></el-input>
      </el-form-item>
      <el-form-item label="电子邮箱" prop="email">
        <el-input v-model="editForm.email"></el-input>
      </el-form-item>
      <el-form-item label="签名" prop="introduction">
        <el-input v-model="editForm.introduction"></el-input>
      </el-form-item>
      <el-form-item label="出生日期" prop="birth">
        <el-date-picker type="date" v-model="editForm.birth"></el-date-picker>
      </el-form-item>
      <el-form-item label="地区" prop="location">
        <el-input v-model="editForm.location"></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="editVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveEdit">确 定</el-button>
      </span>
    </template>
  </el-dialog>

  <!-- 删除提示框 -->
  <yin-del-dialog :delVisible="delVisible" @confirm="confirm" @cancelRow="delVisible = $event"></yin-del-dialog>
</template>

<script lang="ts">
import { defineComponent, getCurrentInstance, watch, ref, reactive, computed } from "vue";
import mixin from "@/mixins/mixin";
import { HttpManager } from "@/api";
import { RouterName } from "@/enums";
import YinDelDialog from "@/components/dialog/YinDelDialog.vue";
import { getBirth } from "@/utils";

export default defineComponent({
  components: {
    YinDelDialog,
  },
  setup() {
    const { proxy } = getCurrentInstance();
    const { changeSex, routerManager, beforeImgUpload } = mixin();

    const tableData = ref([]); // 记录歌曲，用于显示
    const tempDate = ref([]); // 记录歌曲，用于搜索时能临时记录一份歌曲列表
    const pageSize = ref(5); // 页数
    const currentPage = ref(1); // 当前页

    // 计算当前表格中的数据
    const data = computed(() => {
      return tableData.value.slice((currentPage.value - 1) * pageSize.value, currentPage.value * pageSize.value);
    });

    const searchWord = ref(""); // 记录输入框输入的内容
    watch(searchWord, () => {
      if (searchWord.value === "") {
        tableData.value = tempDate.value;
      } else {
        tableData.value = [];
        for (let item of tempDate.value) {
          if (item.username.includes(searchWord.value)) {
            tableData.value.push(item);
          }
        }
      }
    });

    getData();

    // 获取用户信息
    async function getData() {
      tableData.value = [];
      tempDate.value = [];
      const result = (await HttpManager.getAllUser()) as ResponseBody;
      tableData.value = result.data;
      tempDate.value = result.data;
      currentPage.value = 1;
    }
    // 获取当前页
    function handleCurrentChange(val) {
      currentPage.value = val;
    }

    function uploadUrl(id) {
      return HttpManager.attachImageUrl(`/user/avatar/update?id=${id}`);
    }

    /**
     * 路由
     */
    function goCollectPage(id) {
      const breadcrumbList = reactive([
        {
          path: RouterName.Consumer,
          name: "用户管理",
        },
        {
          path: "",
          name: "收藏信息",
        },
      ]);
      proxy.$store.commit("setBreadcrumbList", breadcrumbList);
      routerManager(RouterName.Collect, { path: RouterName.Collect, query: { id } });
    }

    /**
        * 添加
        */
    const centerDialogVisible = ref(false);
    const registerForm = reactive({
      username: "",
      password: "",
      // sex: "1",
      sex: "",
      phoneNum: "",
      email: "",
      birth: new Date(),
      introduction: "",
      location: "",

    });
    const userRule = reactive({
      name: [{ required: true, trigger: "change" }],
      sex: [{ required: true, trigger: "change" }],
      username: [{ required: true, message: "请输入用户名", trigger: "blur" }],
      password: [{ required: true, message: "请输入密码", trigger: "blur" }],
      phoneNum: [{ required: true, message: "请输入手机号", trigger: "blur" }],
      email: [{ required: true, message: "请输入电子邮箱", trigger: "blur" },
      { type: "email", message: "请输入正确的电子邮箱地址", trigger: ["blur", "change"] }],
      introduction: [{ required: true, message: "请输入签名", trigger: "blur" }],
      location: [{ required: true, message: "请输入地区", trigger: "blur" }],
    });


    async function addConsumer() {
      let datetime = getBirth(registerForm.birth);
      let params = new URLSearchParams();
      params.append("username", registerForm.username);
      params.append("password", registerForm.password);
      params.append("sex", registerForm.sex);
      params.append("phoneNum", registerForm.phoneNum);
      params.append("email", registerForm.email);
      params.append("birth", datetime);
      params.append("introduction", registerForm.introduction);
      params.append("location", registerForm.location);

      // 验证用户信息完整性
      if (
        !registerForm.username ||
        !registerForm.password ||
        registerForm.sex === "" ||
        !registerForm.phoneNum ||
        !registerForm.email ||
        !registerForm.introduction ||
        !registerForm.location
      ) {
        (proxy as any).$message({
          message: "请填写完整的用户信息",
          type: "warning",
        });
        return;
      }


      const result = (await HttpManager.setUser(params)) as ResponseBody;
      (proxy as any).$message({
        message: result.message,
        type: result.type,
      });

      if (result.success) {
        getData();
        registerForm.username = "";
        registerForm.password = "";
        registerForm.sex = "";
        registerForm.phoneNum = "";
        registerForm.email = "";
        registerForm.birth = new Date();
        registerForm.introduction = "";
        registerForm.location = "";
      }
      centerDialogVisible.value = false;
    }


    /**
        * 编辑
        */
    const editVisible = ref(false);
    const editForm = reactive({
      id: "",
      username: "",
      password: "",
      sex: "",
      phoneNum: "",
      email: "",
      birth: new Date(),
      introduction: "",
      location: "",
    });

    function editRow(row) {
      editVisible.value = true;
      editForm.id = row.id;
      editForm.username = row.username;
      editForm.password = row.password;
      editForm.sex = row.sex;
      editForm.phoneNum = row.phoneNum;
      editForm.email = row.email;
      editForm.birth = row.birth;
      editForm.introduction = row.introduction;
      editForm.location = row.location;
    }
    async function saveEdit() {
      try {
        let datetime = getBirth(new Date(editForm.birth));
        let params = new URLSearchParams();
        params.append("id", editForm.id);
        params.append("username", editForm.username);
        params.append("password", editForm.password);
        params.append("sex", editForm.sex);
        params.append("phoneNum", editForm.phoneNum);
        params.append("email", editForm.email);
        params.append("birth", datetime);
        params.append("introduction", editForm.introduction);
        params.append("location", editForm.location);

        const result = (await HttpManager.updateUserMsg(params)) as ResponseBody;
        (proxy as any).$message({
          message: result.message,
          type: result.type,
        });

        if (result.success) getData();
        editVisible.value = false;
      } catch (error) {
        console.error(error);
      }
    }
    function handleImgSuccess(response, file) {
      (proxy as any).$message({
        message: response.message,
        type: response.type,
      });
      if (response.success) getData();
    }


    /**
     * 删除
     */
    const idx = ref(-1); // 记录当前要删除的行
    const multipleSelection = ref([]); // 记录当前要删除的列表
    const delVisible = ref(false); // 显示删除框

    async function confirm() {
      const result = (await HttpManager.deleteUser(idx.value)) as ResponseBody;
      (proxy as any).$message({
        message: result.message,
        type: result.type,
      });
      if (result) getData();
      delVisible.value = false;
    }
    function deleteRow(id) {
      idx.value = id;
      delVisible.value = true;
    }
    function handleSelectionChange(val) {
      multipleSelection.value = val;
    }
    function deleteAll() {
      for (let item of multipleSelection.value) {
        deleteRow(item.id);
        confirm();
      }
      multipleSelection.value = [];
    }

    return {
      searchWord,
      data,
      tableData,
      centerDialogVisible,
      editVisible,
      delVisible,
      pageSize,
      currentPage,
      registerForm,
      editForm,
      userRule,
      deleteAll,
      handleSelectionChange,
      handleImgSuccess,
      beforeImgUpload,
      saveEdit,
      attachImageUrl: HttpManager.attachImageUrl,
      changeSex,
      getBirth,
      uploadUrl,
      goCollectPage,
      editRow,
      handleCurrentChange,
      addConsumer,
      confirm,
      deleteRow,
    };
  },
});
</script>

<style scoped>
.singer-img {
  width: 100%;
  height: 80px;
  border-radius: 5px;
  margin-bottom: 5px;
  overflow: hidden;
}
</style>
