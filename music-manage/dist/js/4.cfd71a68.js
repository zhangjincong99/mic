"use strict";(self["webpackChunkmusic_manage"]=self["webpackChunkmusic_manage"]||[]).push([[4],{179:function(e,l,a){a.d(l,{JO:function(){return t},c6:function(){return n},wC:function(){return o}});var t;(function(e){e["BOFANG"]="#icon-bofanganniu",e["ZANTING"]="#icon-zanting"})(t||(t={}));const n="Yin-music 后台管理";var o;(function(e){e["Home"]="/home",e["Info"]="/info",e["Song"]="/song",e["Singer"]="/singer",e["SongList"]="/songList",e["ListSong"]="/listSong",e["Comment"]="/Comment",e["Consumer"]="/consumer",e["Collect"]="/collect",e["Error"]="/404",e["SignIn"]="/",e["SignOut"]="0"})(o||(o={}))},8045:function(e,l,a){a.d(l,{Z:function(){return i}});a(7658);var t=a(3396),n=a(4870),o=a(179);function i(){const{proxy:e}=(0,t.FN)(),l=(0,n.iH)(["jpg","jpeg","png","gif"]);function a(e){return 0===e?"女":1===e?"男":2===e?"组合":3===e?"不明":"男"===e||"女"===e?e:void 0}function i(a){const t=2,n=a.size/1024/1024<t,o=l.value.includes(a.type.replace(/image\//,""));return o||e.$message.error(`图片只支持 ${l.value.join("、")} 格式!`),n||e.$message.error(`上传头像图片大小不能超过 ${t}MB!`),o&&n}function r(l){const a=10,t=l.size/1024/1024<a,n=l.name.substring(l.name.lastIndexOf(".")+1),o="mp3"===n;return o||e.$message({message:"上传文件只能是mp3格式！",type:"error"}),t||e.$message.error(`上传头像图片大小不能超过 ${a}MB!`),o&&t}function u(l,a){switch(l){case o.wC.Song:case o.wC.ListSong:case o.wC.Comment:case o.wC.Consumer:case o.wC.Collect:e.$router.push({path:a.path,query:a.query});break;case o.wC.Home:case o.wC.SignIn:case o.wC.SignOut:case o.wC.Info:case o.wC.Singer:case o.wC.SongList:case o.wC.Error:default:e.$router.push({path:a.path});break}}function d(l=-1){e.$router.go(l)}return{changeSex:a,routerManager:u,goBack:d,beforeImgUpload:i,beforeSongUpload:r}}},2024:function(e,l,a){a.d(l,{o:function(){return t},z:function(){return n}});a(7658);function t(e){if(null==e||""==e)return"";const l=new Date(e),a=l.getFullYear(),t=l.getMonth()+1<10?"0"+(l.getMonth()+1):l.getMonth()+1,n=l.getDate()<10?"0"+l.getDate():l.getDate();return a+"-"+t+"-"+n}function n(e){const l=e.split("\n"),a=/\[\d{2}:\d{2}.(\d{3}|\d{2})\]/g,t=[];if(!/\[.+\]/.test(e))return[e];for(const n of l)if(a.test(n)){const e=n.replace(a,"");t.push(e)}return t}},8968:function(e,l,a){a.d(l,{Z:function(){return s}});var t=a(3396);const n=(0,t._)("div",{class:"del-dialog-cnt",align:"center"},"删除不可恢复，是否确定删除？",-1),o={class:"dialog-footer"};function i(e,l,a,i,r,u){const d=(0,t.up)("el-button"),c=(0,t.up)("el-dialog");return(0,t.wg)(),(0,t.iD)("div",null,[(0,t.Wm)(c,{title:"提示",modelValue:e.centerDialogVisible,"onUpdate:modelValue":l[0]||(l[0]=l=>e.centerDialogVisible=l),width:"300px",center:""},{footer:(0,t.w5)((()=>[(0,t._)("span",o,[(0,t.Wm)(d,{onClick:e.cancelRow},{default:(0,t.w5)((()=>[(0,t.Uk)("取 消")])),_:1},8,["onClick"]),(0,t.Wm)(d,{type:"primary",onClick:e.confirm},{default:(0,t.w5)((()=>[(0,t.Uk)("确 定")])),_:1},8,["onClick"])])])),default:(0,t.w5)((()=>[n])),_:1},8,["modelValue"])])}var r=a(4870),u=(0,t.aZ)({props:{delVisible:Boolean},emits:["cancelRow","confirm"],setup(e){const{proxy:l}=(0,t.FN)(),{delVisible:a}=(0,r.BK)(e),n=(0,r.iH)(a.value);function o(){l.$emit("cancelRow",!1)}function i(){l.$emit("confirm",null)}return(0,t.YP)(a,(e=>{n.value=e})),{centerDialogVisible:n,cancelRow:o,confirm:i}}}),d=a(89);const c=(0,d.Z)(u,[["render",i]]);var s=c},2004:function(e,l,a){a.r(l),a.d(l,{default:function(){return C}});var t=a(3396),n=a(7139);const o={class:"container"},i={class:"handle-box"},r={class:"singer-img"},u=["src"],d={style:{height:"100px",overflow:"scroll"}},c={class:"dialog-footer"},s={class:"dialog-footer"};function m(e,l,a,m,p,g){const f=(0,t.up)("el-button"),w=(0,t.up)("el-input"),b=(0,t.up)("el-table-column"),h=(0,t.up)("el-upload"),W=(0,t.up)("el-table"),_=(0,t.up)("el-pagination"),V=(0,t.up)("el-form-item"),C=(0,t.up)("el-radio"),v=(0,t.up)("el-radio-group"),k=(0,t.up)("el-date-picker"),U=(0,t.up)("el-form"),x=(0,t.up)("el-dialog"),y=(0,t.up)("yin-del-dialog");return(0,t.wg)(),(0,t.iD)(t.HY,null,[(0,t._)("div",o,[(0,t._)("div",i,[(0,t.Wm)(f,{onClick:e.deleteAll},{default:(0,t.w5)((()=>[(0,t.Uk)("批量删除")])),_:1},8,["onClick"]),(0,t.Wm)(w,{placeholder:"筛选歌手",modelValue:e.searchWord,"onUpdate:modelValue":l[0]||(l[0]=l=>e.searchWord=l)},null,8,["modelValue"]),(0,t.Wm)(f,{type:"primary",onClick:l[1]||(l[1]=l=>e.centerDialogVisible=!0)},{default:(0,t.w5)((()=>[(0,t.Uk)("添加歌手")])),_:1})]),(0,t.Wm)(W,{height:"550px",border:"",size:"small",data:e.data,onSelectionChange:e.handleSelectionChange},{default:(0,t.w5)((()=>[(0,t.Wm)(b,{type:"selection",width:"40",align:"center"}),(0,t.Wm)(b,{label:"ID",prop:"id",width:"50",align:"center"}),(0,t.Wm)(b,{label:"歌手图片",prop:"pic",width:"110",align:"center"},{default:(0,t.w5)((l=>[(0,t._)("div",r,[(0,t._)("img",{src:e.attachImageUrl(l.row.pic),style:{width:"100%"}},null,8,u)]),(0,t.Wm)(h,{action:e.uploadUrl(l.row.id),"show-file-list":!1,"on-success":e.handleImgSuccess,"before-upload":e.beforeImgUpload},{default:(0,t.w5)((()=>[(0,t.Wm)(f,null,{default:(0,t.w5)((()=>[(0,t.Uk)("更新图片")])),_:1})])),_:2},1032,["action","on-success","before-upload"])])),_:1}),(0,t.Wm)(b,{label:"歌手",prop:"name",width:"120",align:"center"}),(0,t.Wm)(b,{label:"性别",prop:"sex",width:"60",align:"center"},{default:(0,t.w5)((l=>[(0,t._)("div",null,(0,n.zw)(e.changeSex(l.row.sex)),1)])),_:1}),(0,t.Wm)(b,{label:"出生",prop:"birth",width:"120",align:"center"},{default:(0,t.w5)((l=>[(0,t._)("div",null,(0,n.zw)(e.getBirth(l.row.birth)),1)])),_:1}),(0,t.Wm)(b,{label:"地区",prop:"location",width:"100",align:"center"}),(0,t.Wm)(b,{label:"简介",prop:"introduction"},{default:(0,t.w5)((e=>[(0,t._)("p",d,(0,n.zw)(e.row.introduction),1)])),_:1}),(0,t.Wm)(b,{label:"歌曲管理",width:"120",align:"center"},{default:(0,t.w5)((l=>[(0,t.Wm)(f,{onClick:a=>e.goSongPage(l.row)},{default:(0,t.w5)((()=>[(0,t.Uk)("歌曲管理")])),_:2},1032,["onClick"])])),_:1}),(0,t.Wm)(b,{label:"操作",width:"160",align:"center"},{default:(0,t.w5)((l=>[(0,t.Wm)(f,{onClick:a=>e.editRow(l.row)},{default:(0,t.w5)((()=>[(0,t.Uk)("编辑")])),_:2},1032,["onClick"]),(0,t.Wm)(f,{type:"danger",onClick:a=>e.deleteRow(l.row.id)},{default:(0,t.w5)((()=>[(0,t.Uk)("删除")])),_:2},1032,["onClick"])])),_:1})])),_:1},8,["data","onSelectionChange"]),(0,t.Wm)(_,{class:"pagination",background:"",layout:"total, prev, pager, next","current-page":e.currentPage,"page-size":e.pageSize,total:e.tableData.length,onCurrentChange:e.handleCurrentChange},null,8,["current-page","page-size","total","onCurrentChange"])]),(0,t.Wm)(x,{title:"添加歌手",modelValue:e.centerDialogVisible,"onUpdate:modelValue":l[8]||(l[8]=l=>e.centerDialogVisible=l)},{footer:(0,t.w5)((()=>[(0,t._)("span",c,[(0,t.Wm)(f,{onClick:l[7]||(l[7]=l=>e.centerDialogVisible=!1)},{default:(0,t.w5)((()=>[(0,t.Uk)("取 消")])),_:1}),(0,t.Wm)(f,{type:"primary",onClick:e.addsinger},{default:(0,t.w5)((()=>[(0,t.Uk)("确 定")])),_:1},8,["onClick"])])])),default:(0,t.w5)((()=>[(0,t.Wm)(U,{"label-width":"80px",model:e.registerForm,rules:e.singerRule},{default:(0,t.w5)((()=>[(0,t.Wm)(V,{label:"歌手名",prop:"name"},{default:(0,t.w5)((()=>[(0,t.Wm)(w,{modelValue:e.registerForm.name,"onUpdate:modelValue":l[2]||(l[2]=l=>e.registerForm.name=l)},null,8,["modelValue"])])),_:1}),(0,t.Wm)(V,{label:"性别",prop:"sex"},{default:(0,t.w5)((()=>[(0,t.Wm)(v,{modelValue:e.registerForm.sex,"onUpdate:modelValue":l[3]||(l[3]=l=>e.registerForm.sex=l)},{default:(0,t.w5)((()=>[(0,t.Wm)(C,{label:0},{default:(0,t.w5)((()=>[(0,t.Uk)("女")])),_:1}),(0,t.Wm)(C,{label:1},{default:(0,t.w5)((()=>[(0,t.Uk)("男")])),_:1}),(0,t.Wm)(C,{label:2},{default:(0,t.w5)((()=>[(0,t.Uk)("保密")])),_:1}),(0,t.Wm)(C,{label:2},{default:(0,t.w5)((()=>[(0,t.Uk)("组合")])),_:1}),(0,t.Wm)(C,{label:3},{default:(0,t.w5)((()=>[(0,t.Uk)("不明")])),_:1})])),_:1},8,["modelValue"])])),_:1}),(0,t.Wm)(V,{label:"故乡",prop:"location"},{default:(0,t.w5)((()=>[(0,t.Wm)(w,{modelValue:e.registerForm.location,"onUpdate:modelValue":l[4]||(l[4]=l=>e.registerForm.location=l)},null,8,["modelValue"])])),_:1}),(0,t.Wm)(V,{label:"出生日期",prop:"birth"},{default:(0,t.w5)((()=>[(0,t.Wm)(k,{type:"date",modelValue:e.registerForm.birth,"onUpdate:modelValue":l[5]||(l[5]=l=>e.registerForm.birth=l)},null,8,["modelValue"])])),_:1}),(0,t.Wm)(V,{label:"歌手介绍",prop:"introduction"},{default:(0,t.w5)((()=>[(0,t.Wm)(w,{type:"textarea",modelValue:e.registerForm.introduction,"onUpdate:modelValue":l[6]||(l[6]=l=>e.registerForm.introduction=l)},null,8,["modelValue"])])),_:1})])),_:1},8,["model","rules"])])),_:1},8,["modelValue"]),(0,t.Wm)(x,{title:"编辑",modelValue:e.editVisible,"onUpdate:modelValue":l[15]||(l[15]=l=>e.editVisible=l)},{footer:(0,t.w5)((()=>[(0,t._)("span",s,[(0,t.Wm)(f,{onClick:l[14]||(l[14]=l=>e.editVisible=!1)},{default:(0,t.w5)((()=>[(0,t.Uk)("取 消")])),_:1}),(0,t.Wm)(f,{type:"primary",onClick:e.saveEdit},{default:(0,t.w5)((()=>[(0,t.Uk)("确 定")])),_:1},8,["onClick"])])])),default:(0,t.w5)((()=>[(0,t.Wm)(U,{"label-width":"60px",model:e.editForm,rules:e.singerRule},{default:(0,t.w5)((()=>[(0,t.Wm)(V,{label:"歌手",prop:"name"},{default:(0,t.w5)((()=>[(0,t.Wm)(w,{modelValue:e.editForm.name,"onUpdate:modelValue":l[9]||(l[9]=l=>e.editForm.name=l)},null,8,["modelValue"])])),_:1}),(0,t.Wm)(V,{label:"性别",prop:"sex"},{default:(0,t.w5)((()=>[(0,t.Wm)(v,{modelValue:e.editForm.sex,"onUpdate:modelValue":l[10]||(l[10]=l=>e.editForm.sex=l)},{default:(0,t.w5)((()=>[(0,t.Wm)(C,{label:0},{default:(0,t.w5)((()=>[(0,t.Uk)("女")])),_:1}),(0,t.Wm)(C,{label:1},{default:(0,t.w5)((()=>[(0,t.Uk)("男")])),_:1}),(0,t.Wm)(C,{label:2},{default:(0,t.w5)((()=>[(0,t.Uk)("保密")])),_:1}),(0,t.Wm)(C,{label:2},{default:(0,t.w5)((()=>[(0,t.Uk)("组合")])),_:1}),(0,t.Wm)(C,{label:3},{default:(0,t.w5)((()=>[(0,t.Uk)("不明")])),_:1})])),_:1},8,["modelValue"])])),_:1}),(0,t.Wm)(V,{label:"出生",prop:"birth"},{default:(0,t.w5)((()=>[(0,t.Wm)(k,{type:"date",modelValue:e.editForm.birth,"onUpdate:modelValue":l[11]||(l[11]=l=>e.editForm.birth=l)},null,8,["modelValue"])])),_:1}),(0,t.Wm)(V,{label:"地区",prop:"location"},{default:(0,t.w5)((()=>[(0,t.Wm)(w,{modelValue:e.editForm.location,"onUpdate:modelValue":l[12]||(l[12]=l=>e.editForm.location=l)},null,8,["modelValue"])])),_:1}),(0,t.Wm)(V,{label:"简介",prop:"introduction"},{default:(0,t.w5)((()=>[(0,t.Wm)(w,{type:"textarea",modelValue:e.editForm.introduction,"onUpdate:modelValue":l[13]||(l[13]=l=>e.editForm.introduction=l)},null,8,["modelValue"])])),_:1})])),_:1},8,["model","rules"])])),_:1},8,["modelValue"]),(0,t.Wm)(y,{delVisible:e.delVisible,onConfirm:e.confirm,onCancelRow:l[16]||(l[16]=l=>e.delVisible=l)},null,8,["delVisible","onConfirm"])],64)}a(7658),a(2062);var p=a(4870),g=a(8045),f=a(8968),w=a(2167),b=a(179),h=a(2024),W=(0,t.aZ)({components:{YinDelDialog:f.Z},setup(){const{proxy:e}=(0,t.FN)(),{changeSex:l,routerManager:a,beforeImgUpload:n}=(0,g.Z)(),o=(0,p.iH)([]),i=(0,p.iH)([]),r=(0,p.iH)(5),u=(0,p.iH)(1),d=(0,t.Fl)((()=>o.value.slice((u.value-1)*r.value,u.value*r.value))),c=(0,p.iH)("");async function s(){o.value=[],i.value=[];const e=await w.x.getAllSinger();o.value=e.data,i.value=e.data,u.value=1}function m(e){u.value=e}function f(e){return w.x.attachImageUrl(`/singer/avatar/update?id=${e}`)}function W(l){const t=(0,p.qj)([{path:b.wC.Singer,name:"歌手管理"},{path:"",name:"歌曲信息"}]);e.$store.commit("setBreadcrumbList",t),a(b.wC.Song,{path:b.wC.Song,query:{id:l.id,name:l.name}})}(0,t.YP)(c,(()=>{if(""===c.value)o.value=i.value;else{o.value=[];for(let e of i.value)e.name.includes(c.value)&&o.value.push(e)}})),s();const _=(0,p.iH)(!1),V=(0,p.qj)({name:"",sex:"",birth:new Date,location:"",introduction:""}),C=(0,p.qj)({name:[{required:!0,trigger:"change"}],sex:[{required:!0,trigger:"change"}]});async function v(){let l=(0,h.o)(V.birth),a=new URLSearchParams;a.append("name",V.name),a.append("sex",V.sex),a.append("birth",l),a.append("location",V.location),a.append("introduction",V.introduction);const t=await w.x.setSinger(a);e.$message({message:t.message,type:t.type}),t.success&&(s(),V.birth=new Date,V.name="",V.sex="",V.location="",V.introduction=""),_.value=!1}const k=(0,p.iH)(!1),U=(0,p.qj)({id:"",name:"",sex:"",pic:"",birth:new Date,location:"",introduction:""});function x(e){k.value=!0,U.id=e.id,U.name=e.name,U.sex=e.sex,U.pic=e.pic,U.birth=e.birth,U.location=e.location,U.introduction=e.introduction}async function y(){try{let l=(0,h.o)(new Date(U.birth)),a=new URLSearchParams;a.append("id",U.id),a.append("name",U.name),a.append("sex",U.sex),a.append("birth",l),a.append("location",U.location),a.append("introduction",U.introduction);const t=await w.x.updateSingerMsg(a);e.$message({message:t.message,type:t.type}),t.success&&s(),k.value=!1}catch(l){console.error(l)}}function S(l,a){e.$message({message:l.message,type:l.type}),l.success&&s()}const F=(0,p.iH)(-1),D=(0,p.iH)([]),I=(0,p.iH)(!1);async function $(){const l=await w.x.deleteSinger(F.value);e.$message({message:l.message,type:l.type}),l.success&&s(),I.value=!1}function H(e){F.value=e,I.value=!0}function R(e){D.value=e}function z(){for(let e of D.value)H(e.id),$();D.value=[]}return{searchWord:c,data:d,tableData:o,centerDialogVisible:_,editVisible:k,delVisible:I,pageSize:r,currentPage:u,registerForm:V,editForm:U,singerRule:C,deleteAll:z,handleSelectionChange:R,handleImgSuccess:S,beforeImgUpload:n,saveEdit:y,attachImageUrl:w.x.attachImageUrl,changeSex:l,getBirth:h.o,uploadUrl:f,goSongPage:W,editRow:x,handleCurrentChange:m,addsinger:v,confirm:$,deleteRow:H}}}),_=a(89);const V=(0,_.Z)(W,[["render",m],["__scopeId","data-v-08068c76"]]);var C=V}}]);
//# sourceMappingURL=4.cfd71a68.js.map