import { Skeleton, Typography } from "@mui/material";
import Box from "@mui/material/Box";
import Drawer from "@mui/material/Drawer";
import { grey } from "@mui/material/colors";
import styles from "./Sidebar.module.scss";

const drawerWidth = 240;

interface SidebarProps {
  children?: React.ReactNode;
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

export function Sidebar({ children = Stub }: SidebarProps) {
  return (
    <Box sx={{ display: "flex" }}>
      <Box
        component="nav"
        sx={{ width: { sm: drawerWidth }, flexShrink: { sm: 0 } }}
        aria-label="mailbox folders"
      >
        <Drawer
          PaperProps={{
            sx: {
              background:
                "linear-gradient(180deg, rgba(255, 255, 255, 0.05) 0%, rgba(255, 255, 255, 0.05) 100%), #121212;",
            },
          }}
          variant="permanent"
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
          <div className={styles.container}>{children}</div>
        </Drawer>
      </Box>
    </Box>
  );
}
