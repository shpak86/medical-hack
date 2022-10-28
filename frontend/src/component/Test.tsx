// @ts-nocheck
import { fabric } from 'fabric';
import React, { useEffect, useState } from 'react';
// import api from '../api';

const Editor = () => {
  const [canvas, setCanvas] = useState();
  // const [dicoms, setDicoms] = useState();

  // useEffect(() => {
  //   getDicomImage()

  // }, [])
  // console.log('dicoms', dicoms)

  // const getDicomImage = async () => {
  //   try {
  //     const dicom = await api.dicom.getDicom(111)
  //     console.log('dicom', dicom)
  //     const img = await api.dicom.getDicomImage(dicom.images[0])
  //   } catch (e) {
  //     console.error('getDicom error: ', e)
  //   }
  // }

  const getImage = () => {
    const img = canvas.getObjects().find(function (o) {
      return o.myId === 'myimg'
    });
    return img
  }
  const getInterceptions = () => {
    const img = getImage();
    //TODO только пересечения, если объект не полностью на картинке, то он тоже должен туда попасть ??
    const selectors = canvas.getObjects().filter(function (o) {
      return o.intersectsWithObject(img) && o.myId !== 'myimg'
    });


    const markups = selectors.map(item => {
      const selectorXLeftTop = (item.left - img.left) / img.width
      const selectorYLeftTop = (item.top - img.top) / img.height

      const selectorXRightBottom = (item.left - img.left + item.width) / img.width
      const selectorYRightBottom = (item.top - img.top + item.height) / img.height
      return {
        type: item.type,
        geometry: [[selectorXLeftTop, selectorYLeftTop], [selectorXRightBottom, selectorYRightBottom]]
      }
    })
    console.log('markups', markups)
    console.log(selectors)
  }
  useEffect(() => {
    const canvas = new fabric.Canvas("my-fabric-canvas");

    setCanvas(canvas)
    // canvas.on({
    // 'object:moving': onCanvasChage,
    // 'object:scaling': onChange,
    // 'object:rotating': onChange,
    // });

    return () => {
      canvas.dispose();
    };
  }, []);

  const loadImage = () => {
    fabric.Image.fromURL('../img/mrt.jpeg', function (img) {
      console.log('img', img.width)
      console.log('img', img.height)
      img.myId = 'myimg'
      canvas.add(img);
      canvas.centerObject(img);
      // todo add background image ?
      // canvas.setBackgroundImage(img, canvas.renderAll.bind(canvas), {
      // scaleX: canvas.width / img.width,
      // scaleY: canvas.height / img.height
      // });
    });
  }
  const addRect = () => {
    const img = getImage();
    //todo инициализация по центру картинки ?
    canvas.add(new fabric.Rect(
      { top: img.top + 100, left: img.left + 100, width: 100, height: 100, fill: 'rgba(255, 255, 255, 0.0)', stroke: 'red', type: 'rect' }
    ));
  }
  const getObjects = () => {
    canvas.getObjects().forEach(function (o) {
      console.log(o)
    });
  }
  return <div>
    <button onClick={addRect}>add Rect</button>
    <button onClick={loadImage}>load Image</button>
    <button onClick={getObjects}>Вывести все объекты</button>
    <button onClick={getInterceptions}>Получить разметку на картинке</button>
    <canvas id="my-fabric-canvas" width="1920px" height="900px" />
  </div>
}

export default Editor

//todo
/**
 * Если мы добавляем новый элемент, то мы должны найти все пересечения (getInterceptions) 
 * и вывести их боковым списком с возможностью добавить мета теги. 
 * Или можно добавлять теги при клике на область, вызывая popover, а в боковой менюшке отображать все это
 */

/**
 * Добавить компонент с запросом списка dicom с бэка и их отображением
 */
