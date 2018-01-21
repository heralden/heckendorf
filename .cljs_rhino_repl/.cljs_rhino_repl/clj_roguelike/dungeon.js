// Compiled by ClojureScript 1.9.908 {}
goog.provide('clj_roguelike.dungeon');
goog.require('cljs.core');
clj_roguelike.dungeon.create_area = (function clj_roguelike$dungeon$create_area(w,h){
return new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"width","width",-384071477),w,new cljs.core.Keyword(null,"tiles","tiles",178505240),cljs.core.vec.call(null,cljs.core.repeat.call(null,(w * h),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"tile","tile",758132866),new cljs.core.Keyword(null,"wall","wall",-787661558)], null)))], null);
});
clj_roguelike.dungeon.yx__GT_i = (function clj_roguelike$dungeon$yx__GT_i(w,p__39017){
var vec__39018 = p__39017;
var y = cljs.core.nth.call(null,vec__39018,(0),null);
var x = cljs.core.nth.call(null,vec__39018,(1),null);
return (x + (y * w));
});
clj_roguelike.dungeon.i__GT_yx = (function clj_roguelike$dungeon$i__GT_yx(w,i){
return new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [((i / w) | (0)),cljs.core.mod.call(null,i,w)], null);
});
clj_roguelike.dungeon.next_id = (function clj_roguelike$dungeon$next_id(){
return (0);
});
clj_roguelike.dungeon.coord_range = (function clj_roguelike$dungeon$coord_range(c,d){
return cljs.core.map.call(null,(function (p1__39021_SHARP_){
return (c + p1__39021_SHARP_);
}),cljs.core.range.call(null,(0),d));
});
clj_roguelike.dungeon.indexes_rect = (function clj_roguelike$dungeon$indexes_rect(w,h,p__39022,width){
var vec__39023 = p__39022;
var y = cljs.core.nth.call(null,vec__39023,(0),null);
var x = cljs.core.nth.call(null,vec__39023,(1),null);
var ys = clj_roguelike.dungeon.coord_range.call(null,y,h);
var xs = clj_roguelike.dungeon.coord_range.call(null,x,w);
var iter__35336__auto__ = ((function (ys,xs,vec__39023,y,x){
return (function clj_roguelike$dungeon$indexes_rect_$_iter__39026(s__39027){
return (new cljs.core.LazySeq(null,((function (ys,xs,vec__39023,y,x){
return (function (){
var s__39027__$1 = s__39027;
while(true){
var temp__4657__auto__ = cljs.core.seq.call(null,s__39027__$1);
if(temp__4657__auto__){
var xs__5205__auto__ = temp__4657__auto__;
var y__$1 = cljs.core.first.call(null,xs__5205__auto__);
var iterys__35332__auto__ = ((function (s__39027__$1,y__$1,xs__5205__auto__,temp__4657__auto__,ys,xs,vec__39023,y,x){
return (function clj_roguelike$dungeon$indexes_rect_$_iter__39026_$_iter__39028(s__39029){
return (new cljs.core.LazySeq(null,((function (s__39027__$1,y__$1,xs__5205__auto__,temp__4657__auto__,ys,xs,vec__39023,y,x){
return (function (){
var s__39029__$1 = s__39029;
while(true){
var temp__4657__auto____$1 = cljs.core.seq.call(null,s__39029__$1);
if(temp__4657__auto____$1){
var s__39029__$2 = temp__4657__auto____$1;
if(cljs.core.chunked_seq_QMARK_.call(null,s__39029__$2)){
var c__35334__auto__ = cljs.core.chunk_first.call(null,s__39029__$2);
var size__35335__auto__ = cljs.core.count.call(null,c__35334__auto__);
var b__39031 = cljs.core.chunk_buffer.call(null,size__35335__auto__);
if((function (){var i__39030 = (0);
while(true){
if((i__39030 < size__35335__auto__)){
var x__$1 = cljs.core._nth.call(null,c__35334__auto__,i__39030);
cljs.core.chunk_append.call(null,b__39031,clj_roguelike.dungeon.yx__GT_i.call(null,width,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [y__$1,x__$1], null)));

var G__39032 = (i__39030 + (1));
i__39030 = G__39032;
continue;
} else {
return true;
}
break;
}
})()){
return cljs.core.chunk_cons.call(null,cljs.core.chunk.call(null,b__39031),clj_roguelike$dungeon$indexes_rect_$_iter__39026_$_iter__39028.call(null,cljs.core.chunk_rest.call(null,s__39029__$2)));
} else {
return cljs.core.chunk_cons.call(null,cljs.core.chunk.call(null,b__39031),null);
}
} else {
var x__$1 = cljs.core.first.call(null,s__39029__$2);
return cljs.core.cons.call(null,clj_roguelike.dungeon.yx__GT_i.call(null,width,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [y__$1,x__$1], null)),clj_roguelike$dungeon$indexes_rect_$_iter__39026_$_iter__39028.call(null,cljs.core.rest.call(null,s__39029__$2)));
}
} else {
return null;
}
break;
}
});})(s__39027__$1,y__$1,xs__5205__auto__,temp__4657__auto__,ys,xs,vec__39023,y,x))
,null,null));
});})(s__39027__$1,y__$1,xs__5205__auto__,temp__4657__auto__,ys,xs,vec__39023,y,x))
;
var fs__35333__auto__ = cljs.core.seq.call(null,iterys__35332__auto__.call(null,xs));
if(fs__35333__auto__){
return cljs.core.concat.call(null,fs__35333__auto__,clj_roguelike$dungeon$indexes_rect_$_iter__39026.call(null,cljs.core.rest.call(null,s__39027__$1)));
} else {
var G__39033 = cljs.core.rest.call(null,s__39027__$1);
s__39027__$1 = G__39033;
continue;
}
} else {
return null;
}
break;
}
});})(ys,xs,vec__39023,y,x))
,null,null));
});})(ys,xs,vec__39023,y,x))
;
return iter__35336__auto__.call(null,ys);
});
clj_roguelike.dungeon.add_room = (function clj_roguelike$dungeon$add_room(w,h,yx,area){
var is = clj_roguelike.dungeon.indexes_rect.call(null,w,h,yx,new cljs.core.Keyword(null,"width","width",-384071477).cljs$core$IFn$_invoke$arity$1(area));
return cljs.core.reduce.call(null,((function (is){
return (function (p1__39034_SHARP_,p2__39035_SHARP_){
return cljs.core.assoc_in.call(null,p1__39034_SHARP_,new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"tiles","tiles",178505240),p2__39035_SHARP_,new cljs.core.Keyword(null,"tile","tile",758132866)], null),new cljs.core.Keyword(null,"empty","empty",767870958));
});})(is))
,area,is);
});

//# sourceMappingURL=dungeon.js.map
