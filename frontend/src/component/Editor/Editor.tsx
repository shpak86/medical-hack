import { FabricJSCanvas, useFabricJSEditor } from 'fabricjs-react'

const Editor = () => {
  const { editor, onReady } = useFabricJSEditor()
  const onAddCircle = () => {
    editor?.addCircle()
  }
  const onAddRectangle = () => {
    editor?.addRectangle()
  }
  const onAddLine = () => {
    editor?.addLine()
  }

  return (
    <>
    <button onClick={onAddCircle}>Add circle</button>
    <button onClick={onAddRectangle}>Add Rectangle</button>
    <button onClick={onAddLine}>Add Line</button>
    <FabricJSCanvas className="sample-canvas" onReady={onReady} />
    </>
  )
}

export default Editor