import { GithubOutlined } from '@ant-design/icons';
import { DefaultFooter } from '@ant-design/pro-components';
const Footer: React.FC = () => {
  const defaultMessage = '若凌永远不困';
  const currentYear = new Date().getFullYear();
  return (
    <DefaultFooter
      copyright={`${currentYear} ${defaultMessage}`}
      links={[
        {
          key: 'github',
          title: <div><span>一起学习 </span><GithubOutlined /></div>,
          href: 'https://github.com/Evan-xwl/SSO',
          blankTarget: true,
        }
      ]}
    />
  );
};
export default Footer;
