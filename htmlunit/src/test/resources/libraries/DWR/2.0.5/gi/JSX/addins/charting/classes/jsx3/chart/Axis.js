/*
 * Copyright (c) 2001-2007, TIBCO Software Inc.
 * Use, modification, and distribution subject to terms of license.
 */
jsx3.require("jsx3.chart.ChartComponent");jsx3.Class.defineClass("jsx3.chart.Axis",jsx3.chart.ChartComponent,null,function(k,q){var dc=jsx3.vector;var sc=dc.Stroke;var hb=jsx3.chart;k.TICK_INSIDE="inside";k.TICK_OUTSIDE="outside";k.TICK_CROSS="cross";k.TICK_NONE="none";k.LABEL_HIGH="high";k.LABEL_LOW="low";k.LABEL_AXIS="axis";k.Cy={inside:1,outside:1,cross:1};k.Hg={axis:1,high:1,low:1};k.Yj=1;k.fw=2;k.Hw=4;k.Pf=3;k.BF=7;k.Ux=8;k.vk=6;k.Tu=5;k.nz=[k.Pf,k.Hw,k.fw,k.Yj,k.fw,k.Yj,k.Pf,k.Hw,k.Ux,k.vk,k.BF,k.Tu];k.Le=10;k.Tw=50;k.nj=12;k.percent=function(c){return c+"%";};k.scientific=function(l,j){if(l==0)return "0";if(j==null)j=2;var cb=l<0;l=Math.abs(l);var U=Math.floor(Math.log(l)/Math.LN10);var ac=U!=0?l/Math.pow(10,U):l;ac=ac.toString();var oc=ac.indexOf(".");if(oc>=0){if(ac.length-oc-1>j)ac=ac.substring(0,oc+1+j);}return (cb?"-":"")+ac+"e"+U;};q.init=function(l,o,f){this.jsxsuper(l);this.horizontal=o!=null?jsx3.Boolean.valueOf(o):null;this.primary=f!=null?jsx3.Boolean.valueOf(f):null;this.length=100;this.showAxis=jsx3.Boolean.TRUE;this.axisStroke="#000000";this.showLabels=jsx3.Boolean.TRUE;this.labelGap=3;this.labelRotation=0;this.labelPlacement=k.LABEL_AXIS;this.tickLength=3;this.tickPlacement=k.TICK_OUTSIDE;this.tickStroke="#000000";this.minorTickDivisions=4;this.minorTickLength=3;this.minorTickPlacement=k.TICK_NONE;this.minorTickStroke=null;this.labelFunction=null;this.labelClass=null;this.labelStyle=null;this.labelColor=null;this.displayWidth=null;};q.a1=jsx3.Method.newAbstract("index");q.RQ=jsx3.Method.newAbstract();q.vX=jsx3.Method.newAbstract();q.g6=jsx3.Method.newAbstract();q.getHorizontal=function(){return this.horizontal;};q.setHorizontal=function(n){this.horizontal=n;};q.sX=function(){return this.primary;};q.SQ=function(g){this.primary=g;};q.getLength=function(){return this.length;};q.setLength=function(j){this.length=j;};q.getShowAxis=function(){return this.showAxis;};q.setShowAxis=function(e){this.showAxis=e;};q.getLabelFunction=function(){return this.o_("labelFunction");};q.setLabelFunction=function(p){this.uR("labelFunction",p);};q.getAxisStroke=function(){return this.axisStroke;};q.setAxisStroke=function(l){this.axisStroke=l;};q.getShowLabels=function(){return this.showLabels;};q.setShowLabels=function(h){this.showLabels=h;};q.getLabelGap=function(){return this.labelGap;};q.setLabelGap=function(b){this.labelGap=b;};q.getLabelRotation=function(){return this.labelRotation;};q.setLabelRotation=function(r){this.labelRotation=r;};q.getLabelPlacement=function(){return this.labelPlacement;};q.setLabelPlacement=function(p){if(k.Hg[p]){this.labelPlacement=p;}else{throw new jsx3.IllegalArgumentException("labelPlacement",p);}};q.getTickLength=function(){return this.tickLength;};q.setTickLength=function(l){this.tickLength=l;};q.getTickPlacement=function(){return this.tickPlacement;};q.setTickPlacement=function(o){if(k.Cy[o]||o==k.TICK_NONE){this.tickPlacement=o;}else{throw new jsx3.IllegalArgumentException("tickPlacement",o);}};q.getTickStroke=function(){return this.tickStroke;};q.setTickStroke=function(m){this.tickStroke=m;};q.getMinorTickDivisions=function(){return this.minorTickDivisions;};q.setMinorTickDivisions=function(l){this.minorTickDivisions=l;};q.getMinorTickLength=function(){return this.minorTickLength;};q.setMinorTickLength=function(r){this.minorTickLength=r;};q.getMinorTickPlacement=function(){return this.minorTickPlacement;};q.setMinorTickPlacement=function(d){if(k.Cy[d]||d==k.TICK_NONE){this.minorTickPlacement=d;}else{throw new jsx3.IllegalArgumentException("minorTickPlacement",d);}};q.getMinorTickStroke=function(){return this.minorTickStroke;};q.setMinorTickStroke=function(f){this.minorTickStroke=f;};q.getLabelClass=function(){return this.labelClass;};q.setLabelClass=function(s){this.labelClass=s;};q.getLabelStyle=function(){return this.labelStyle;};q.setLabelStyle=function(e){this.labelStyle=e;};q.getLabelColor=function(){return this.labelColor;};q.setLabelColor=function(b){this.labelColor=b;};q.getDisplayWidth=function(){if(this.displayWidth!=null){return this.displayWidth;}else{return this.horizontal?k.nj:k.Tw;}};q.setDisplayWidth=function(p){this.displayWidth=p;};q.updateView=function(){this.jsxsuper();var J=this.l5();var fc=this.getWidth();var rc=this.getHeight();var zc=this.getOpposingAxis();if(zc==null)return;var Mc=this.Uq(zc);this.A3();if(this.showAxis){var z=new dc.Line(0,0,0,0,0,0);J.appendChild(z);var tc=sc.valueOf(this.axisStroke);if(tc==null)tc=new sc();z.setStroke(tc);if(this.horizontal)z.fS(0,Mc,this.length,Mc);else z.fS(Mc,0,Mc,this.length);}var Fc=this.RQ();if(k.Cy[this.tickPlacement]&&this.tickLength>0){var U=new dc.LineGroup(0,0,fc,rc);J.appendChild(U);var tc=sc.valueOf(this.tickStroke);U.setStroke(tc);var N=this.si(this.tickPlacement,this.tickLength);var R=N[0];var C=Mc+N[1];this.S7(U,Fc,C,R);}if(k.Cy[this.minorTickPlacement]&&this.minorTickLength>0){var ub=new dc.LineGroup(0,0,fc,rc);J.appendChild(ub);var tc=sc.valueOf(this.minorTickStroke);ub.setStroke(tc);var N=this.si(this.minorTickPlacement,this.minorTickLength);var R=N[0];var C=Mc+N[1];var Qb=0;for(var Nc=0;Nc<Fc.length;Nc++){var Fb=this.RY(Fc,Nc);this.S7(ub,Fb,C,R);Qb=Fc[Nc];}if(Qb<this.length){var Fb=this.RY(Fc,Fc.length);this.S7(ub,Fb,C,R);}}var _=this.Yh(Mc);var wc=this.getAxisTitle();if(wc!=null&&wc.getDisplay()!=jsx3.gui.Block.DISPLAYNONE){var yb=_[5];var O=this.horizontal&&this.primary||!this.horizontal&&!this.primary?0:-1;if(this.horizontal){var kb=yb+O*wc.getPreferredHeight();wc.setDimensions(0,kb,this.length,wc.getPreferredHeight());}else{var B=yb+O*wc.getPreferredWidth();wc.setDimensions(B,0,wc.getPreferredWidth(),this.length);}wc.updateView();J.appendChild(wc.l5());}if(this.showLabels){var Kc=this.w4();var K=new dc.Group(0,0,fc,rc);J.appendChild(K);var Lb=_[0];var W=_[1];var O=_[2];this._jsxQE=null;for(var Nc=0;Nc<Kc.length;Nc++){var G=Kc[Nc];var Db=null;if(this.horizontal){var Q=Nc>0?(Kc[Nc-1]+Kc[Nc])/2:null;var ob=Nc<Kc.length-1?(Kc[Nc+1]+Kc[Nc])/2:null;if(ob==null&&Q!=null)ob=2*Kc[Nc]-Q;else{if(Q==null&&ob!=null)Q=2*Kc[Nc]-ob;}if(ob==null){Q=Kc[Nc]-50;ob=Kc[Nc]+50;}Db=Math.round(ob-Q);}else{Db=this.getDisplayWidth()-this.mj()-this.labelGap;}if(this.horizontal){var Mb=Math.round(G-Db/2);var Sb=W+O*Math.round(k.Le/2);this.nq(K,Mb,Sb,Mb+Db,Sb,this.Qu(Nc));}else{var Mb=O==1?W:W-Db;this.nq(K,Mb,G,Mb+Db,G,this.Qu(Nc));}}}};q.nq=function(j,n,b,i,p,h){if(!(h&&h.toString().match(/\S/)))return;var Dc=new dc.TextLine(n,b,i,p,h);Dc.setClassName(this.labelClass);Dc.setExtraStyles(this.labelStyle);Dc.setColor(this.labelColor);j.appendChild(Dc);};q.Qu=function(m){var eb=this.a1(m);var jb=this.getLabelFunction();return jb!=null?jb.call(null,eb):eb!=null?eb.toString():"";};q.mj=function(){var lc=this.tickPlacement==k.TICK_OUTSIDE||this.tickPlacement==k.TICK_CROSS?this.tickLength:0;var hc=this.minorTickPlacement==k.TICK_OUTSIDE||this.minorTickPlacement==k.TICK_CROSS?this.minorTickLength:0;return Math.max(lc,hc);};q.Uq=function(r){if(r==null){r=this.getOpposingAxis();if(r==null)return 0;}if(r.vX())return r.getCoordinateFor(0);else{if(this.primary)return this.horizontal?r.getLength():0;else return this.horizontal?0:r.getLength();}};q.si=function(g,h){var K=0;if(g==k.TICK_CROSS){K=-1*h;h=h*2;}else{var Ub=0;if(this.horizontal)Ub++;if(this.primary)Ub++;if(g==k.TICK_INSIDE)Ub++;if(Ub%2==1)K=-1*h;}return [h,K];};q.Yh=function(n){var Eb=this.getOpposingAxis();if(n==null)n=this.Uq(Eb);var lc=0;if(this.horizontal)lc=lc|1;if(this.primary)lc=lc|2;if(this.labelPlacement==k.LABEL_LOW)lc=lc|4;else{if(this.labelPlacement==k.LABEL_AXIS)lc=lc|8;}var Ob=k.nz[lc];var Sb=0;var Lb=0;if(this.tickPlacement==k.TICK_OUTSIDE||this.tickPlacement==k.TICK_CROSS)Sb=this.tickLength;if(this.tickPlacement==k.TICK_INSIDE||this.tickPlacement==k.TICK_CROSS)Lb=this.tickLength;if(this.minorTickPlacement==k.TICK_OUTSIDE||this.minorTickPlacement==k.TICK_CROSS)Sb=Math.max(Sb,this.minorTickLength);if(this.minorTickPlacement==k.TICK_INSIDE||this.minorTickPlacement==k.TICK_CROSS)Lb=Math.max(Lb,this.minorTickLength);var Db=null,y=null,Kb=null;switch(Ob){case k.Yj:case k.Pf:y=-1;Db=-this.labelGap;Db=Db-Math.max(0,Sb-n);break;case k.fw:case k.Hw:y=1;Db=Eb.getLength()+this.labelGap;Db=Db+Math.max(0,Sb+n-Eb.getLength());break;case k.BF:case k.vk:y=-1;Db=n-this.labelGap-Sb;break;case k.Ux:case k.Tu:y=1;Db=n+this.labelGap+Sb;break;default:hb.LOG.error("bad placement value: "+Ob);}if(this.showLabels){if(this.horizontal)Kb=Db+y*k.Le;else Kb=Db+y*this.getDisplayWidth();}else{Kb=Db;}if(this.horizontal&&this.primary||!this.horizontal&&!this.primary){Kb=Math.max(Kb,Eb.getLength());}else{Kb=Math.min(Kb,0);}return [Ob,Db,y,Sb,Lb,Kb];};q.x1=function(){var H=0,Bc=0;var Lc=this.getOpposingAxis();if(Lc==null)return [0,0];var yc=this.Uq(Lc);var Mc=this.Yh(yc);var z=this.getAxisTitle();var fc=Mc[1];var jb=Mc[2];var K=Mc[3];var Ub=Mc[4];if(this.showLabels){if(this.horizontal)fc=fc+jb*k.Le;else fc=fc+jb*this.getDisplayWidth();}if(fc<0){H=-fc;}else{if(fc>Lc.getLength()){Bc=fc-Lc.getLength();}}if(Ub>this.length-yc)Bc=Math.max(Bc,Ub+this.length-yc);if(K>-yc)H=Math.max(H,K-yc);if(z!=null&&z.getDisplay()!=jsx3.gui.Block.DISPLAYNONE){if(this.horizontal)Bc=Bc+z.getPreferredHeight();else H=H+z.getPreferredWidth();}return [H,Bc];};q.S7=function(e,h,o,g){if(this.horizontal){for(var u=0;u<h.length;u++)e.aT(h[u],o,0,g);}else{for(var u=0;u<h.length;u++)e.aT(o,h[u],g,0);}};q.w4=function(){return this.RQ();};q.RY=function(a,o){var Fc=[];if(o==0){return [];}else{if(o==a.length){return [];}else{var Y=a[o-1];var fc=a[o];for(var jc=1;jc<this.minorTickDivisions;jc++){Fc.push(Math.round(Y+jc/this.minorTickDivisions*(fc-Y)));}}}return Fc;};q.getAxisTitle=function(){return hb.ChartLabel?this.getFirstChildOfType(hb.ChartLabel):null;};q.getOpposingAxis=function(){var B=this.getChart();if(B==null)return null;if(this.horizontal){if(this.primary){return B.getPrimaryYAxis();}else{return B.getSecondaryYAxis();}}else{if(this.primary){return B.getPrimaryXAxis();}else{return B.getSecondaryXAxis();}}};q.onSetChild=function(f){if((hb.ChartLabel&&f instanceof hb.ChartLabel)&&this.getAxisTitle()==null){f.setLabelRotation(this.horizontal?hb.ChartLabel.ROTATION_NORMAL:hb.ChartLabel.ROTATION_CCW);return true;}return false;};q.onSetParent=function(c){return hb.Chart&&c instanceof hb.Chart;};q.toString=function(){return "[Axis '"+this.getName()+"']";};k.getVersion=function(){return hb.q2;};});
