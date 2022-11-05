import { CssBaseline, Skeleton, Typography } from "@mui/material";
import Drawer from "@mui/material/Drawer";
import { grey } from "@mui/material/colors";
import styles from "./Sidebar.module.scss";
import TagSelector from "../TagSelector";

const drawerWidth = 240;

interface SidebarProps {
  children?: React.ReactNode;
  miniature?: React.ReactNode;
}
const Stub = [...Array(5)].map(() => (
  <Skeleton
    sx={{ bgcolor: grey[800], margin: "20px 8px" }}
    animation="wave"
    variant="rectangular"
    width={220}
    height={150}
  />
));

export function Sidebar({ children, miniature = Stub }: SidebarProps) {
  return (
    <div className={styles.root}>
      <CssBaseline />
      <Drawer
        variant="permanent"
        className={styles.drawer}
        PaperProps={{
          sx: {
            background:
              "linear-gradient(180deg, rgba(255, 255, 255, 0.05) 0%, rgba(255, 255, 255, 0.05) 100%), #121212;",
            width: drawerWidth,
          },
        }}
        sx={{
          display: { xs: "none", sm: "block" },
          "& .MuiDrawer-paper": {
            boxSizing: "border-box",
            width: drawerWidth,
          },
        }}
        open
      >
        <div className={styles.logo}>
          <Typography variant="h6" color="white">
            МосКомАртель
          </Typography>
        </div>
        <TagSelector />
        <div className={styles.container}>{miniature}</div>
      </Drawer>
      <main className={styles.content}>
        <div className={styles.toolbar} />
        {children}
      </main>
    </div>
  );
}
