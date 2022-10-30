import {
  AppBar,
  Toolbar,
  ButtonGroup,
  Button,
  Typography,
  Slider,
} from "@mui/material";
import { grey } from "@mui/material/colors";
import styles from "./Header.module.scss";

const drawerWidth = 240;
const buttonStyle = { color: grey[400], borderColor: grey[400] };

export function Header() {
  return (
    <AppBar
      position="fixed"
      sx={{
        width: { sm: `calc(100% - ${drawerWidth}px)` },
        ml: { sm: `${drawerWidth}px` },
      }}
    >
      <Toolbar
        sx={{ bgcolor: grey[800], display: "flex", alignItems: "center" }}
      >
        <ButtonGroup variant="outlined" aria-label="outlined button group">
          <Button sx={buttonStyle}>КВАДРАТ</Button>
          <Button sx={buttonStyle}>ЛИНЕЙКА</Button>
          <Button sx={buttonStyle}>ОЧИСТИТЬ</Button>
        </ButtonGroup>
        <div className={styles.sliderContainer}>
          <Typography
            sx={{...buttonStyle, marginRight: 2}}
            variant="subtitle2"
          >
            КОНТРАСТ:
          </Typography>
          <Slider
            defaultValue={50}
            aria-label="Slider"
            valueLabelDisplay="off"
          />
        </div>
      </Toolbar>
    </AppBar>
  );
}
